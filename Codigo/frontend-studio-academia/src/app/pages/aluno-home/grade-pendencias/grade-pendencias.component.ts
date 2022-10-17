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
  fileToUpload: File | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private monthlyPaymentService : MonthlyPaymentService
    ) { }

  ngOnInit(): void {
    this.comprovanteForm = this.formBuilder.group({
      file :['',[]],
      message : ['',[]]
    })
    this.listarTodosPagamentos(0,10,2);
    this.listarPagamentosPendentes(0,10,2);
  }

  onFileSelected(event: any) {
  try {
    event.target.files.item(0);
  this.comprovanteForm.value["file"]

    console.log(event.target.files.item(0));
    this.monthlyPaymentService.sendFile(this.comprovanteForm.value["file"]).subscribe({
      next:(res) => {
        console.log(res)
        alert("Comprovante enviado com êxito")
      },
      error: (err) => {
        console.log(err)
        alert("Não foi enviar o  comprovante")
      }
    })
    console.log(event)



  }catch (e) {

  }


  }

  public onSubmit() : void{
    const teste = this.comprovanteForm.getRawValue()
    let body = {
      payment_voucher : this.comprovanteForm.value['file'],
      //message : this.comprovanteForm.value['message'],
    }

    const formData: FormData = new FormData();
    this.monthlyPaymentService.sendFile(formData).subscribe({
      next:(res) => {
        console.log(res)
        alert("Comprovante enviado com êxito")
      },
      error: (err) => {
        console.log(err)
        alert("Não foi enviar o  comprovante")
      }
    })
    console.log(teste)
  }

  public listarTodosPagamentos(page : number, size : number, idUser : number) {
    this.monthlyPaymentService.listarPagamentoUsuario(page,size,idUser).subscribe((res : any)=>{
       this.pageable$ = res
       this.pagamentos =<MonthlyPayments>this.pageable$.content
    })
  }

  public listarPagamentosPendentes(page : number, size : number, idUser : number) {
    this.monthlyPaymentService.listarPagamentoPendentesUsuario(page,size,idUser,'AGUARDANDO_PAGAMENTO').subscribe((res : any)=>{
       this.pageablePend$ = res
       this.pagamentosPendentes =<MonthlyPayments> this.pageablePend$.content
    })
  }

}
