import { MonthlyPayment } from './../../../Models/monthly-payment';
import { Component, OnInit } from '@angular/core';
import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';

@Component({
  selector: 'app-grade-geral-pagamentos',
  templateUrl: './grade-geral-pagamentos.component.html',
  styleUrls: ['./grade-geral-pagamentos.component.css']
})
export class GradeGeralPagamentosComponent implements OnInit {

  pagamentos !: MonthlyPayments
  pageable !: pageableObject


  constructor(private monthlyPaymentService: MonthlyPaymentService) { }

  ngOnInit(): void {
    this.listarTodosPagamentosEmAnalise()
  }


  listarTodosPagamentosEmAnalise(){
    this.monthlyPaymentService.listarTodosPagamentosPendentes(0,10,"AGUARDANDO_PAGAMENTO").subscribe({
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
      },
      error: (err) => {
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }

}
