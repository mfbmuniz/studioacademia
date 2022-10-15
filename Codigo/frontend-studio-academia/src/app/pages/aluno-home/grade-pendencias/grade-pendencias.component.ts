import { Observable } from 'rxjs';
import { MonthlyPayment } from './../../../Models/monthly-payment';
import { MonthlyPaymentService } from './../../../services/monthly-paymentService';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-grade-pendencias',
  templateUrl: './grade-pendencias.component.html',
  styleUrls: ['./grade-pendencias.component.css']
})
export class GradePendenciasComponent implements OnInit {

  comprovanteForm !: FormGroup
  pagamentos !: MonthlyPayments
  pageable$ !: pageableObject
  pagamentosPendentes !: MonthlyPayments
  pageablePend$ !: pageableObject
  constructor(
    private formBuilder: FormBuilder,
    private monthlyPaymentService : MonthlyPaymentService
    ) { }

  ngOnInit(): void {
    this.comprovanteForm = this.formBuilder.group({
      file :['',[]],
    })
    this.listarTodosPagamentos(0,10,1);
    this.listarPagamentosPendentes(0,10,1);
  }

  public onSubmit() : void{
    const teste = this.comprovanteForm.getRawValue()
    console.log(teste)
  }

  public listarTodosPagamentos(page : number, size : number, idUser : number) {
    this.monthlyPaymentService.listarPagamentoUsuario(page,size,idUser).subscribe((res : any)=>{
       this.pageable$ = res
       this.pagamentos =<MonthlyPayments>this.pageable$.content
    })
  }

  public listarPagamentosPendentes(page : number, size : number, idUser : number) {
    this.monthlyPaymentService.listarPagamentoPendentesUsuario(page,size,idUser).subscribe((res : any)=>{
       this.pageablePend$ = res
       this.pagamentosPendentes =<MonthlyPayments> this.pageablePend$.content
    })
  }

}
