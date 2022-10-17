import { HttpClient } from '@angular/common/http';
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


  public sendFile(formData : FormData){

    return this.http.post(`${MonthlyPaymentService.API_URLS.ROOT}/uploadImage`,formData)
  }

    /**
   * Listar pagamentos pendentes do usuario
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
  public listarPagamentoPendentesUsuario(page : number, size : number, idUser : number, paymentStatusRequest: String) : Observable<any>{
    paymentStatusRequest = "AGUARDANDO_PAGAMENTO";
    return this.http.get(`${environment.apiUrl}/monthly-payment/pageAllUserPendency/${page}/size/${size}/idUser/${idUser}/paymentStatusRequest/${paymentStatusRequest}`)
  }


}
