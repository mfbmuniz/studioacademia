import { Component, OnInit } from '@angular/core';
import {UserRoleService} from "../../../services/UserRoleService";

@Component({
  selector: 'app-adm-sidebar',
  templateUrl: './adm-sidebar.component.html',
  styleUrls: ['./adm-sidebar.component.css']
})
export class AdmSidebarComponent implements OnInit {

  public isAdmin : boolean = false;
  constructor(private userRoleService:UserRoleService ) {

  }

  ngOnInit(): void {
    this.isAdmin= this.userRoleService.isAdmin();
  }

}
