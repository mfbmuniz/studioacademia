import {MonthlyPaymentService} from './../../../services/monthly-paymentService';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MonthlyPayments} from 'src/app/Models/monthly-payment';
import {pageableObject} from 'src/app/Models/PageableObject';
import {AuthService} from "../../../services/AuthService";


@Component({
  selector: 'app-grade-pendencias',
  templateUrl: './grade-pendencias.component.html',
  styleUrls: ['./grade-pendencias.component.css']
})
export class GradePendenciasComponent implements OnInit {

  comprovanteForm !: FormGroup
  fechados !: FormGroup
  pagamentos !: MonthlyPayments
  pageable$ !: pageableObject
  pagamentosPendentes !: MonthlyPayments
  pagamentosPagos !: MonthlyPayments
  pageablePend$ !: pageableObject
  pageablePagos$ !: pageableObject
  fileToUpload: File | null = null;
  erro : boolean = true
  path : any = null;
  public types = ['PAGO', 'ATRASADO', 'AGUARDANDO_PAGAMENTO', 'EM_ANALISE', 'NAO_RECEBIDO' , 'TODOS']
  actualUser : any


  constructor(
    private formBuilder: FormBuilder,
    private monthlyPaymentService : MonthlyPaymentService,
    private authServeice : AuthService

    ) {

      }

  ngOnInit(): void {
    this.comprovanteForm = this.formBuilder.group({
      file :['',[]],
      message : ['',[]]

    })


    this.actualUser = this.authServeice.getSession().user.id
    this.listarPagamentosPendentes(0,10,this.actualUser);
    this.listarPagamentosPagos(0,10,this.actualUser);


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

  public onSubmit(index : any){

    if( (!this.erro) && (this.path!=null) ) {
      let body = {
        paymentVoucher: this.path["0"],
        message: this.comprovanteForm.value['message'],
        dueDate: this.pagamentos[index].dueDate,
        userId:  this.pagamentos[index].userId,
        monthlyPaymentId : this.pagamentos[index].monthlyPaymentId

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
       this.pagamentosPendentes = <MonthlyPayments> this.pageablePend$.content
    })
  }

  public listarPagamentosPagos(page : number, size : number, idUser : number) {
    this.monthlyPaymentService.listarPagamentoPendentesUsuario(page,size,idUser,'PAGO').subscribe((res : any)=>{
      this.pageablePagos$ = res
      this.pagamentosPagos = <MonthlyPayments> this.pageablePagos$.content
    })
  }

  public listarPagamentosEspecificos(page : number, size : number, idUser : number, paymentStatus : String) {
    this.monthlyPaymentService.listarPagamentoPendentesUsuario(page,size,idUser,paymentStatus).subscribe((res : any)=>{
      this.pageable$ = res
      this.pagamentos =<MonthlyPayments>this.pageable$.content
    })
  }

  specificSearch(value: any) {

    let parameter =value?.target?.value ;

    if ( value?.target?.value == "TODOS"){
      this.listarTodosPagamentos(0,10,2);
    }else{
      this.listarPagamentosEspecificos(0,10,2,parameter)
    }


  }
}
