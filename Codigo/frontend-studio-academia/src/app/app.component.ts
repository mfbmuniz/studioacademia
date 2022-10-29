import { Component } from '@angular/core';
import {AuthService} from "./services/AuthService";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-studio-academia';
  constructor(private authService: AuthService) {
  }

  getSession(){
    return this.authService.getSession();
  }
}
