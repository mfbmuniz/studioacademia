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


  constructor(private monthlyPaymentService: MonthlyPaymentService,
              private sanitizer:DomSanitizer) { }


  ngOnInit(): void {
    this.listarTodosPagamentosEmAnalise()
  }

  sanitize(url:any){
    console.log('--->')

    console.log(url)

    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  transform(url:any) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url)
  }


  listarTodosPagamentosEmAnalise(){
    this.monthlyPaymentService.listarTodosPagamentosPendentes(0,10).subscribe({
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

}
