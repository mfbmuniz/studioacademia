import { MonthlyPayments } from 'src/app/Models/monthly-payment';
import { Component, Input, OnInit } from '@angular/core';
import { PageableObject } from 'src/app/Models/PageableObject';
import { AuthService } from 'src/app/services/AuthService';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';

@Component({
  selector: 'app-pag-alert',
  templateUrl: './pag-alert.component.html',
  styleUrls: ['./pag-alert.component.css']
})
export class PagAlertComponent implements OnInit {

  pageable_Atradados$ !: PageableObject

  pageable_NaoRecebidos$ !: PageableObject

  actualUser: any;

  constructor(
    private monthlyPaymentService: MonthlyPaymentService,
    private authServeice: AuthService
  ) { }

  ngOnInit(): void {
    this.actualUser = this.authServeice.getSession().user.id
    this.listarPagamentosAtrasados(0,10,this.actualUser)
    this.listarPagamentosNaoRecebidos(0,10,this.actualUser)
  }

  public listarPagamentosAtrasados(page: number, size: number, idUser: number) {
    this.monthlyPaymentService.listarPagamentoUsuarioPorChaveDeBusca(page, size, idUser, "ATRASADO")
      .subscribe({
        next: (res: any) => {
          this.pageable_Atradados$ = res

        },
        error(err) {
            console.log(err)
        },
      })
  }

  public listarPagamentosNaoRecebidos(page: number, size: number, idUser: number) {
    this.monthlyPaymentService.listarPagamentoUsuarioPorChaveDeBusca(page, size, idUser, 'NAO_RECEBIDO')
      .subscribe({
        next: (res: any) => {
          this.pageable_NaoRecebidos$ = res

        },
        error(err) {
            console.log(err)
        },
      })
  }
}
