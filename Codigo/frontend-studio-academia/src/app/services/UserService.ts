import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class  UserService {

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/user/create`,
    EDIT: `${environment.apiUrl}/user/edit`,
    DELETE: `${environment.apiUrl}/user/delete`
  };

  constructor(private http: HttpClient, private router: Router) {}

  private sessionSubject = new BehaviorSubject<any>(
    this.getSession()
  );

  create(body: any): Observable<any> {
    return this.http.post(UserService.API_URLS.CREATE, body);
  }

  edit(body: any): Observable<any> {
    return this.http.post(UserService.API_URLS.EDIT, body);
  }

  listByPage(pageRequest: number, sizeRequest: number, keySearch: string){
    let searchPageUrl = `${environment.apiUrl}/user/page/${pageRequest}/size/${sizeRequest}`

    if(keySearch.length > 0){
      searchPageUrl +='/name/'+keySearch
    }

    return this.http.get(searchPageUrl)
  }

  findUser(userId : String){
    return this.http.get(`${environment.apiUrl}/user/getuserbyid/userId/${userId}`)
  }

  delete(email : String): Observable<any>{
   // let deleteUsuario = `${environment.apiUrl}/delete/${email}`
    return this.http.delete(`${environment.apiUrl}/user/delete/${email}`)
  }

  getSession() {
    return JSON.parse(localStorage.getItem("session") || "{}");
  }
}
