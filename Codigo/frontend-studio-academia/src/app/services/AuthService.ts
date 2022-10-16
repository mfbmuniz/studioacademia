import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class  AuthService {
  private static API_URLS = {
    LOGIN: `${environment.apiUrl}/auth/login`,
  };

  constructor(private http: HttpClient, private router: Router) {}

  private sessionSubject = new BehaviorSubject<any>(
    this.getSession()
  );

  login(body: any): Observable<any> {
    return this.http.post(AuthService.API_URLS.LOGIN, body);
  }

  logout() {
    localStorage.clear();
    this.sessionSubject.next(null);
    return {}
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

}
