import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/AuthService';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-aluno-sidebar',
  templateUrl: './aluno-sidebar.component.html',
  styleUrls: ['./aluno-sidebar.component.css']
})
export class AlunoSidebarComponent implements OnInit {

  actualUser !: any

  pageableAtrasado$ !: pageableObject
  pageableNaoRecebido$ !: pageableObject
  pageableAguardandoPagamento$ !: pageableObject

  constructor(
    private monthlyPaymentService :MonthlyPaymentService,
    private authService : AuthService,) { }

  ngOnInit(): void {
    this.actualUser = this.authService.getSession().user.id
    this.notification_Atrasado(this.actualUser)
    this.notification_AguardandoPagamento(this.actualUser)
    this.notification_NaoRecebido(this.actualUser)
  }

  notification_Atrasado(idUser:number){
    this.monthlyPaymentService.listarPagamentoUsuarioPorChaveDeBusca(0,10,idUser,"ATRASADO").subscribe({
      next : (res) =>{
        this.pageableAtrasado$ = res;

      },
      error(err) {
          console.log(err)
      },
    })
  }

  notification_NaoRecebido(idUser:number){
    this.monthlyPaymentService.listarPagamentoUsuarioPorChaveDeBusca(0,10,idUser,"NAO_RECEBIDO").subscribe({
      next : (res) =>{
        this.pageableNaoRecebido$ = res;

      },
      error(err) {
          console.log(err)
      },
    })
  }

  notification_AguardandoPagamento(idUser:number){
    this.monthlyPaymentService.listarPagamentoUsuarioPorChaveDeBusca(0,10,idUser,"AGUARDANDO_PAGAMENTO").subscribe({
      next : (res) =>{
        this.pageableAguardandoPagamento$ = res;


      },
      error(err) {
          console.log(err)
      },
    })
  }

}
