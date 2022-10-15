import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserFileService {

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/user-files/create`,
    ADDEXERCISES : `${environment.apiUrl}/user-files/addExercises`,

  };

  constructor(private http: HttpClient, private router: Router) { }

  create(body: any): Observable<any> {
    return this.http.post(UserFileService.API_URLS.CREATE, body);
  }

  delete(idFile : String): Observable<any>{
    return this.http.delete(`${environment.apiUrl}/user-files/deleteFile/${idFile}`)
  }

  addExercise(body: any): Observable<any>{
    return this.http.post(UserFileService.API_URLS.ADDEXERCISES, body);
  }

  editExercise(body : any , idFile : String , idExercise : String) : Observable<any>{
    return this.http.post(`${environment.apiUrl}/user-files/editExercises/${idFile}/${idExercise}`,body)
  }

  deleteExercise(idFile : String , idExercise : String): Observable<any>{
    return this.http.delete(`${environment.apiUrl}/user-files/deleteExercise/${idFile}/${idExercise}`)
  }

  public fihcaUsuario(page : number, size : number, idUser : number , fileId : number){
    return this.http.get(`${environment.apiUrl}/user-files/pageExercisesInFile/
    ${page}/size/${size}/idUser/${idUser}/fileId/${fileId}`)
    //
  }



}
