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
    DELETE : `${environment.apiUrl}/physical-assessment/delete`,
  };

  create(body : any){
    return this.http.post(PhysicalAssessmentService.API_URLS.CREATE,body)
  }

  update(body : any){
    return this.http.post(PhysicalAssessmentService.API_URLS.UPDATE,body)
  }

  delete(physicalAssessmentId : number){
    return this.http.delete(`${PhysicalAssessmentService.API_URLS.DELETE}/physicalAssessmentId/${physicalAssessmentId}`)
  }

  listPhysicalAssessment(page : number , size : number){
    return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}`)
  }

  listPhysicalAssessmentByUserId(page : number , size : number, idUser : string){
    if(idUser.length > 0)
      return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}/idUser/${idUser}`)
    else
    return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}`)
  }

  listPhysicalAssessmentByProfessional(page : number , size : number, professionalId : string){
    return this.http.get(`${environment.apiUrl}/physical-assessment/page/${page}/size/${size}/professionalId/${professionalId}`)
  }

  getPhysicalAssessmentById(physicalAssessmentId : string){
    return this.http.get(`${environment.apiUrl}/physical-assessment/getphysicalassessmentbyid/physicalAssessmentId/${physicalAssessmentId}`)
  }
}
