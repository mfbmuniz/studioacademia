import { MonthlyPayment } from './../../../Models/monthly-payment';
import { Component, OnInit } from '@angular/core';
import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';
import {DomSanitizer} from '@angular/platform-browser';
import { Pipe, PipeTransform } from '@angular/core';



@Component({
  selector: 'app-grade-geral-pagamentos',
  templateUrl: './grade-geral-pagamentos.component.html',
  styleUrls: ['./grade-geral-pagamentos.component.css']
})


export class GradeGeralPagamentosComponent implements OnInit, PipeTransform {




  pagamentos !: MonthlyPayments
  pageable !: pageableObject
  actualMonthlyPayment !: MonthlyPayment
  image!: any
  imageToShow: any;
  isImageLoading: any;
  currentPage !: number;
  public types = ['PAGO', 'ATRASADO', 'AGUARDANDO_PAGAMENTO', 'EM_ANALISE', 'NAO_RECEBIDO']


  constructor(private monthlyPaymentService: MonthlyPaymentService,
              private sanitizer:DomSanitizer) { }


  ngOnInit(): void {
    this.currentPage=0;
    this.listarTodosPagamentosEmAnalise()
  }

  sanitize(url:any){
    // console.log('--->')

    // console.log(url)

    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  transform(url:any) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url)
  }


  listarTodosPagamentosEmAnalise(){
    this.monthlyPaymentService.listarTodosPagamentosPendentes(this.currentPage,10).subscribe({
      next : (res)=>{
        this.pageable = res
        this.pagamentos = <MonthlyPayments> this.pageable?.content
      },
      error : (err)=>{
        console.log(err)
        alert("Não foi possível listar os pagamentos")
      }
    })
  }


  search(){

  }

  download() {
    alert("BAIXANDO ARQUIVO")
  }

  aprove(idMonthlyRequest: any,body : MonthlyPayment) {
    this.monthlyPaymentService.aprovePayment(idMonthlyRequest,body).subscribe({
      next: (res) => {
        alert("Pagamento aprovado")
        location.reload()
      },
      error: (err) => {
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }

  reprove(idMonthlyRequest: any,body : MonthlyPayment) {
    this.monthlyPaymentService.reprovePayment(idMonthlyRequest,body).subscribe({
      next: (res) => {
        alert("Pagamento recusado")
        location.reload()
      },
      error: (err) => {
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }

  removeUnsafe(paymentVoucher: any) {

    return paymentVoucher.substring(6,paymentVoucher.length)

  }


  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.imageToShow = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
  getImage(monthlyPaymentId: any) {
    this.isImageLoading = true;
    this.monthlyPaymentService.getImage(monthlyPaymentId).subscribe(data =>{
        this.createImageFromBlob(data);
        this.isImageLoading = false;
      },
      error => {
      this.isImageLoading = false;
      this.imageToShow = null;
      console.log(error);
    });
  }

  specificSearch(value: any) {

    let parameter =value?.target?.value ;
    this.currentPage = 0;
    this.listarPagamentosEspecificos(this.currentPage,10,parameter)
  }

  public listarPagamentosEspecificos(page : number, size : number, paymentStatus : string) {
    this.monthlyPaymentService.pageAllPendencyByKeySearch(page,size,paymentStatus).subscribe((res : any)=>{
      this.pageable = res
      this.pagamentos =<MonthlyPayments>this.pageable.content
   })
  }



  nextPage(){
    if(this.pagamentos.length != 0){
      this.currentPage++;
      this.listarTodosPagamentosEmAnalise()
    }
  }



  previousPage(){
    if(this.currentPage !=0){
      this.currentPage--;
      this.listarTodosPagamentosEmAnalise()
    }
  }
}
