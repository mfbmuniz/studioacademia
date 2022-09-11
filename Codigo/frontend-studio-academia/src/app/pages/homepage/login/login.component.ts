import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../../services/AuthService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public hasError: Boolean = false;
  public formLogin: FormGroup = new FormGroup(
    {
      email: new FormControl("", [Validators.required]),
      password:new FormControl("", [Validators.required])
    }
  );
  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  public logar() : void{
    let body = {
      email: this.formLogin.value["email"],
      password: this.formLogin.value["password"]
    }
    console.log("body")
    console.log(
      body
    )
    this.authService.login(body)
      .subscribe(
        {
          next:(res) => {
            console.log(res)

            localStorage.setItem(
              'session',
              JSON.stringify(res)
            );

            this.defineDefaultRouteByRole(res?.roles[0]);
          },
          error: (err) => {
            this.hasError = true;
            console.log(err)
          }
        }
      );
  }

  private defineDefaultRouteByRole(role: any) {
    switch (role) {
      case "ADMIN":
        this.router.navigate(["/adm"]);
        break;
      case "ALUNO":
        this.router.navigate(["/home"]);
        break;
      default:
        if (this.router.url !== "/login")
          console.error("User type not allowed");

    }
  }

}
