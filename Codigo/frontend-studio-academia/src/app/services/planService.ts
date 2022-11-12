import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class PlanService {

  constructor(private http: HttpClient,) { }

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/plans/create`,
    EDIT : `${environment.apiUrl}/plans/create`,
    DELETE : `${environment.apiUrl}/plans/delete/planId`
  };

  create(body : any){
    return this.http.post(PlanService.API_URLS.CREATE,body)
  }

  edit(body : any){
    return this.http.post(PlanService.API_URLS.EDIT,body)
  }

  delete(planId : number){
    return this.http.delete(`${PlanService.API_URLS.DELETE}/${planId}`)
  }

  listPlans(page : number , size : number){
    return this.http.get(`${environment.apiUrl}/plans/page/${page}/size/${size}`)
  }

}
