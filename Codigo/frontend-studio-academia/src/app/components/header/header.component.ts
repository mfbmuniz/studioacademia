import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import { AuthService } from 'src/app/services/AuthService';
import {Router} from "@angular/router";
import {LoginComponent} from "../../pages/homepage/login/login.component";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit  {

  @Input() public user$: any;
  public _isLogged: boolean = false;

  constructor(
    public authService: AuthService,
    public router: Router,
  ) { }

  ngOnInit(): void {

    this._isLogged = false;
    this.user$ = this.authService.getSession()
    console.log(this.user$.source.value.user.name)
  }

  navigateToHome() {

    if(this.user$?.roles.includes('ADMIN')){
      this.router.navigate(['adm'])
    }else if(this.user$?.roles.includes('ALUNO')){
      this.router.navigate(['aluno'])
    }else if(this.user$?.roles.includes('NUTRICIONISTA')){
      this.router.navigate(['adm'])
    }else if(this.user$?.roles.includes('PROFESSOR')){
      this.router.navigate(['adm'])
    }
  }

  logout() {
    this.user$ = this.authService.logout();
    this.router.navigate(['home'])
  }

}
