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

  //

  listUserFilesByPageWithSize (pageRequest: number, sizeRequest: number,idUser : String, keySearch:String): Observable<any>{

        let searchPageUrl = `${environment.apiUrl}/user-files/page/${pageRequest}/size/${sizeRequest}/iduser/${idUser}`

    if(keySearch.length > 0){
      searchPageUrl +='/name/'+keySearch
    }

    return this.http.get(searchPageUrl)
  }

  listUserExercisesByPageWithSize (pageRequest: number, sizeRequest: number,fileId:String):Observable<any> {

    let searchPageUrl = `${environment.apiUrl}/user-files/pageExercisesInFile/${pageRequest}/size/${sizeRequest}/fileId/${fileId}`

    return this.http.get(searchPageUrl)
  }
  /**
   *
   * @param page Página que deseja visualizar iniciando em 0", example = "0"
   * @param size Quantidade de ficha a serem listados por página", example = "10"
   * @param idUser id do usário
   * @param fileId id da ficha
   * @returns
   */
  public fileExerciseuser(page : number, size : number, idUser : number , fileId : number){
    return this.http.get(`${environment.apiUrl}/user-files/pageExercisesInFile/
    ${page}/size/${size}/idUser/${idUser}/fileId/${fileId}`)
    //
  }



}
