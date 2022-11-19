import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PhysicalAssessmentService {

  constructor(private http: HttpClient,) { }

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/physical-assessment/create`,
    UPDATE : `${environment.apiUrl}/physical-assessment/edit`,
    DELETE : `${environment.apiUrl}/physical-assessment/delete/planId`,
  };

  create(body : any){
    return this.http.post(PhysicalAssessmentService.API_URLS.CREATE,body)
  }

  update(body : any){
    return this.http.post(PhysicalAssessmentService.API_URLS.UPDATE,body)
  }

  delete(planId : number){
    return this.http.delete(`${PhysicalAssessmentService.API_URLS.DELETE}/${planId}`)
  }

  listPhysicalAssessment(page : number , size : number){
    return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}`)
  }

  listPhysicalAssessmentByName(page : number , size : number, name : string){
    return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}/name/${name}`)
  }
}
