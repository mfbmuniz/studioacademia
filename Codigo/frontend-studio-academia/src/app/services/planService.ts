import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class PlanService {

  constructor(private http: HttpClient,) { }



  listPlans(page : number , size : number){
    return this.http.get(`${environment.apiUrl}/plans/page/${page}/size/${size}`)
  }

}
