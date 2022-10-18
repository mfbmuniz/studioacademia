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
  erro : boolean = true
  path : any = null;
  monthlyPayment !: MonthlyPayment


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

    const paymentVoucherImage: FormData = new FormData();
    paymentVoucherImage.append('paymentVoucherImage',event.target.files.item(0));

    console.log(event.target.files.item(0));

    console.log("imagem : ");
    console.log(paymentVoucherImage);


    this.monthlyPaymentService.sendFile(paymentVoucherImage).subscribe({
      next:(res) => {
        console.log(res)
        //alert("Comprovante enviado com êxito")
        this.erro=false;
        this.path = res
      },
      error: (err) => {
        console.log(err)
        //alert("Não foi enviar o  comprovante")
        this.erro=true;
      }
    })
    console.log(event)



  }catch (e) {
      console.log("erro, catch")
      console.log(e)
  }


  }

  public onSubmit() : void{

    if( (!this.erro) && (this.path!=null) ) {
      let body = {
        paymentVoucher: this.path["0"],
        message: this.comprovanteForm.value['message'],
        dueDate: this.monthlyPayment.dueDate,
        userId:  this.monthlyPayment.userId,
        monthlyPaymentId : this.monthlyPayment.monthlyPaymentId

      }

      this.monthlyPaymentService.createRequestForApprove(body).subscribe({
        next: (res) => {
          console.log(res)
          alert("Comprovante enviado com êxito")
        },
        error: (err) => {
          console.log(err)
          alert("Não foi enviar a requisição")
        }
      })
    }else{
      alert("Não foi possivel carregar a imagem do comprovante")
    }

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
