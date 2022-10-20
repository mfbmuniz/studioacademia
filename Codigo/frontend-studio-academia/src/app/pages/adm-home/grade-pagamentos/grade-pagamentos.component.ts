import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';

@Component({
  selector: 'app-grade-pagamentos',
  templateUrl: './grade-pagamentos.component.html',
  styleUrls: ['./grade-pagamentos.component.css']
})
export class GradePagamentosComponent implements OnInit {

  pagamentos !: MonthlyPayments
  idAluno !: string
  pageable !: pageableObject
  //@Input() pagamentos !: MonthlyPayments
  constructor(private monthlyPaymentService : MonthlyPaymentService,
    private routeAc : ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno']);
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

  aprove(idMonthlyRequest : any){
    this.monthlyPaymentService.aprovePayment(idMonthlyRequest).subscribe({
      next : (res)=>{
        alert("Pagamento aprovado")
      },
      error : (err)=>{
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }

  reprove(idMonthlyRequest : any){
    this.monthlyPaymentService.reprovePayment(idMonthlyRequest).subscribe({
      next : (res)=>{
        alert("Pagamento recusado")
      },
      error : (err)=>{
        console.log(err)
        alert("Não foi possível realizar a ação")
      }
    })
  }
}
