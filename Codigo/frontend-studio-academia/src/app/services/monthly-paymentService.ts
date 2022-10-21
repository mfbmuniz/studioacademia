import {HttpClient, HttpEvent} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import {BehaviorSubject, Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class MonthlyPaymentService {

  private static API_URLS = {
    ROOT:`${environment.apiUrl}/monthly-payment`,
  };

  constructor(private http: HttpClient, private router: Router) { }


  public  sendFile (paymentVoucherImage : FormData){

    return this.http.post(`${MonthlyPaymentService.API_URLS.ROOT}/uploadImage`,paymentVoucherImage)
  }

  public  createRequestForApprove (body: any): Observable<any> {

    return this.http.post(`${MonthlyPaymentService.API_URLS.ROOT}/createRequestForApprove`,body)
  }
    /**
   * Listar todos os pagamentos  do usuario
   * @param page Página que deseja visualizar iniciando em 0, example = "0"
   * @param size Quantidade de itens a serem listados por página", example = "10"
   * @param idUser id do usuário
   * @returns Observable<any>
   */
  public listarPagamentoUsuario(page : number, size : number, idUser : number) : Observable<any>{
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllUser/${page}/size/${size}/${idUser}`)
  }

  /**
   * Listar pagamentos pendentes do usuario
   * @param page Página que deseja visualizar iniciando em 0, example = "0"
   * @param size Quantidade de itens a serem listados por página", example = "10"
   * @param idUser id do usuário
   * @returns Observable<any>
   */
  public  listarPagamentoPendentesUsuario(page : number, size : number, idUser : number, paymentStatusRequest: String) : Observable<any>{
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllUserPendency/page/${page}/size/${size}/idUser/${idUser}/paymentStatusRequest/${paymentStatusRequest}`)

  }

  /**
   *
   * @param page Quantidade de itens a serem listados por página", example = "10"
   * @param size Quantidade de itens a serem listados por página", example = "10"
   * @param paymentStatusRequest Types : [PAGO, AGUARDANDO_PAGAMENTO,ATRASADO,EM_ANALISE, NAO_RECEBIDO]
   * @returns Observable<MonthlyPayments>
   */
  public listarTodosPagamentosPendentes(page : number, size : number ,  paymentStatusRequest: String){
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllPendency/${page}/size/${size}/paymentStatusRequest/${paymentStatusRequest}`)
  }

  //Tem q olhar se tá dando certo
  public aprovePayment(idMonthlyRequest : number){
    return this.http.get(`${environment.apiUrl}/monthly-payment/aproveRequest/${idMonthlyRequest}`)
  }

  //Tem q olhar se tá dando certo
  public reprovePayment(idMonthlyRequest : number){
    return this.http.get(`${environment.apiUrl}/monthly-payment/reproveRequest/${idMonthlyRequest}`)
  }

}
