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

  addExercises(body: any){
    return this.http.post(UserFileService.API_URLS.ADDEXERCISES, body);
  }

  delete(idFile : String){
    return this.http.delete(`${environment.apiUrl}/user-files/deleteFile/${idFile}`)
  }

}
