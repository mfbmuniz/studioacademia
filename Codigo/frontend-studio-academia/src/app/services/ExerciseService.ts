import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class ExerciseService {
  private static API_URLS = {
    EXERCICE_REGISTRATION: `${environment.apiUrl}/exercise/create`,
  };

  constructor(private http: HttpClient, private router: Router) {}

  private sessionSubject = new BehaviorSubject<any>(
    this.getSession()
  );

  login(body: any): Observable<any> {
    return this.http.post(ExerciseService.API_URLS.EXERCICE_REGISTRATION, body);
  }

  logout() {
    localStorage.clear();
    this.sessionSubject.next(null);
  }

  getSession() {
    return JSON.parse(localStorage.getItem("session") || "{}");
  }

  isAuth() {
    return !!localStorage.getItem("session");
  }

  getObservableSession(): Observable<any> {
          this.sessionSubject = new BehaviorSubject<any>(
              this.getSession()
          );
          return this.sessionSubject.asObservable();
  }

  listByPage(pageRequest: number, sizeRequest: number, keySearch: string){
    let searchPageUrl = `${environment.apiUrl}/exercise/page/${pageRequest}/size/${sizeRequest}`

    if(keySearch.length > 0){
      searchPageUrl +='/name/'+keySearch
    }

    return this.http.get(searchPageUrl)
  }
}
