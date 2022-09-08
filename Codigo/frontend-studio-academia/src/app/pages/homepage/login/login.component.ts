import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario = '';
  senha = '';
  origem ='';

  constructor() { }

  ngOnInit(): void {
  }

  public logar() : void{
    console.log(this.usuario + "  " + this.senha + "  " + this.origem)

  }

}
