import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";
import { pageableObject } from '../Models/PageableObject';
import { Plan } from '../Models/plan';
@Injectable({
  providedIn: 'root'
})
export class PlanService {

  constructor(private http: HttpClient,) { }

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/plans/create`,
    UPDATE : `${environment.apiUrl}/plans/create`,
    DELETE : `${environment.apiUrl}/plans/delete/planId`,
    GETPLANBYID : `${environment.apiUrl}/plans/getplanbyid/planId`,
  };

  create(body : any){
    return this.http.post(PlanService.API_URLS.CREATE,body)
  }

  update(body : any){
    return this.http.post(PlanService.API_URLS.UPDATE,body)
  }

  delete(planId : number){
    return this.http.delete(`${PlanService.API_URLS.DELETE}/${planId}`)
  }

  listPlans(page : number , size : number){
    return this.http.get(`${environment.apiUrl}/plans/page/${page}/size/${size}`)
  }

  getPlanById(planId : number): Observable<pageableObject>{
    return this.http.get(`${PlanService.API_URLS.GETPLANBYID}/${planId}`)
  }
}
