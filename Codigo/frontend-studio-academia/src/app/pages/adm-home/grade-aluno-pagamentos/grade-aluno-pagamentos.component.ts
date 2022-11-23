import { MonthlyPayment } from './../../../Models/monthly-payment';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';


@Component({
  selector: 'app-grade-aluno-pagamentos',
  templateUrl: './grade-aluno-pagamentos.component.html',
  styleUrls: ['./grade-aluno-pagamentos.component.css']
})
export class GradeAlunoPagamentosComponent implements OnInit {

  pagamentos !: MonthlyPayments
  idAluno !: string
  pageable !: pageableObject
  image!: any
  imageToShow: any;
  isImageLoading: any;

  constructor(private monthlyPaymentService : MonthlyPaymentService,
    private routeAc : ActivatedRoute,) {
    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno']);

   }

  ngOnInit(): void {
    this.listPayments(this.idAluno)
  }

  listPayments(idUser : any){
    this.monthlyPaymentService.listarPagamentoUsuario(0,10,idUser).subscribe({
      next : (res) =>{
        this.pageable = res;
        this.pagamentos = <MonthlyPayments>this.pageable?.content
      }
    })
  }


  download(){
    alert("BAIXANDO ARQUIVO")
  }

  aprove(idMonthlyRequest : any, body : MonthlyPayment){
    this.monthlyPaymentService.aprovePayment(idMonthlyRequest, body).subscribe({
      next : (res)=>{
        alert("Pagamento aprovado")
        location.reload()
      },
      error : (err)=>{
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }

  reprove(idMonthlyRequest : any, body : MonthlyPayment){
    this.monthlyPaymentService.reprovePayment(idMonthlyRequest,body).subscribe({
      next : (res)=>{
        alert("Pagamento recusado")
        location.reload()
      },
      error : (err)=>{
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
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
