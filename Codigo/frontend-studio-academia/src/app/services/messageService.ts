import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";
import { Messages } from "../Models/comments";

@Injectable({
  providedIn: "root",
})
export class  MessageService {

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/message-service/create`,
    EDIT: `${environment.apiUrl}/message-service/edit`,

  };

  constructor(private http: HttpClient, private router: Router) {}

  create(body: any): Observable<any> {
    return this.http.post(MessageService.API_URLS.CREATE, body);
  }

  ListMessages(page : number, size : number):Observable<any> {
    return this.http.get(`${environment.apiUrl}/message-service/page/${page}/size/${size}`)
  }


 }
