import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/AuthService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user$ = this.authService.getObservableSession()

  constructor(private authService : AuthService) { }

  ngOnInit(): void {
  }

}
