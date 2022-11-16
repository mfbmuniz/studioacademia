import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import {BehaviorSubject, Observable} from "rxjs";
import {MonthlyPayment} from "../Models/monthly-payment";


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

  public  getImage (idMonthlyPayment: number): Observable<Blob> {

    return this.http.get(`${MonthlyPaymentService.API_URLS.ROOT}/getImage/idMonthlyPayment/${idMonthlyPayment}`,{ responseType: 'blob' });

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
  public  listarPagamentoPendentesUsuario(page : number, size : number, idUser : number) : Observable<any>{
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllUserPendency/page/${page}/size/${size}/idUser/${idUser}`)

  }


  /**
   *
   * @param page Quantidade de itens a serem listados por página", example = "10"
   * @param size Quantidade de itens a serem listados por página", example = "10"
   * @param paymentStatusRequest Types : [PAGO, AGUARDANDO_PAGAMENTO,ATRASADO,EM_ANALISE, NAO_RECEBIDO]
   * @returns Observable<MonthlyPayments>
   */
  public listarTodosPagamentosPendentes(page : number, size : number ){
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllPendency/${page}/size/${size}`)
  }




  public aprovePayment(idMonthlyRequest : number, body : any){
    return this.http.post(`${environment.apiUrl}/monthly-payment/aproveRequest/${idMonthlyRequest}`, body)
  }


  public reprovePayment(idMonthlyRequest : number, body : any){
    return this.http.post(`${environment.apiUrl}/monthly-payment/reproveRequest/${idMonthlyRequest}`,body)
  }

  public  listarPagamentoUsuarioPorChaveDeBusca(page : number, size : number, idUser : number, paymentStatusRequest : String ) : Observable<any>{

    return this.http.get(
      `${environment.apiUrl}/monthly-payment/pageUserWithKeySearch/page/${page}/size/${size}/idUser/${idUser}/paymentStatusRequest/${paymentStatusRequest}`)

  }

  public pageAllPendencyByKeySearch(page : number, size : number, key : string){
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllWithKeySearch/${page}/size/${size}/paymentStatusRequest/${key}`)
  }

}



