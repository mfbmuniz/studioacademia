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
    EDIT: `${environment.apiUrl}/user-files/edit`,
    ADDEXERCISES : `${environment.apiUrl}/user-files/addExercises`,
    FIND_EXERCISES: `${environment.apiUrl}/user-files/getUserFileExercise/fileId/`

  };

  constructor(private http: HttpClient, private router: Router) { }

  create(body: any): Observable<any> {
    return this.http.post(UserFileService.API_URLS.CREATE, body);
  }

  edit(body: any): Observable<any> {
    return this.http.post(UserFileService.API_URLS.EDIT, body);
  }

  delete(idFile : String): Observable<any>{
    return this.http.delete(`${environment.apiUrl}/user-files/deleteFile/${idFile}`)
  }

  addExercises(body: any): Observable<any>{
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

  public listarFichasUser(page : number, size : number, idUser : number){
    return this.http.get(`${environment.apiUrl}/user-files/page/${page}/size/${size}/iduser/${idUser}`)
  }

  public pegarFichaUser(idFile : number){
    return this.http.get(`${environment.apiUrl}/user-files/getUserFileExercise/fileId/${idFile}`)
  }

  public getExercisesFromList (userFileId: number): Observable<any>{

    return this.http.get(UserFileService.API_URLS.FIND_EXERCISES+userFileId)
  }

}
