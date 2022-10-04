import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import {BehaviorSubject, Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class MonthlyPaymentService {

  private static API_URLS = {
    CREATE: `${environment.apiUrl}/`,
  };

  constructor(private http: HttpClient, private router: Router) { }


}
