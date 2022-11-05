import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class  messageService {

  private static API_URLS = {
    URL: `${environment.apiUrl}/message-service/`,
  };

  constructor(private http: HttpClient,) {}

  create(body : any){
    return this.http.post(`${URL}/create`,body)
  }


 }
