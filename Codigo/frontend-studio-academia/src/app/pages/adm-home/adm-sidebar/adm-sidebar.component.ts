import { Component, OnInit } from '@angular/core';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';
import {UserRoleService} from "../../../services/UserRoleService";

@Component({
  selector: 'app-adm-sidebar',
  templateUrl: './adm-sidebar.component.html',
  styleUrls: ['./adm-sidebar.component.css']
})
export class AdmSidebarComponent implements OnInit {

  pageableAtrasados !: pageableObject
  pageableAnalises !: pageableObject

  public isAdmin : boolean = false;
  constructor(private userRoleService:UserRoleService,
    private monthlyPaymentService :MonthlyPaymentService, ) {

  }

  ngOnInit(): void {
    this.isAdmin= this.userRoleService.isAdmin();
    if(this.isAdmin){
      this.notificationAtrasados();
      this.notificationEmAnalise()
    }

  }

  notificationAtrasados(){
    this.monthlyPaymentService.pageAllPendencyByKeySearch(0,10,'ATRASADO').subscribe({
      next: (res) => {
        this.pageableAtrasados = res
      },
      error(err) {
          console.log(err)
      },
    })
  }

  notificationEmAnalise(){
    this.monthlyPaymentService.pageAllPendencyByKeySearch(0,10,'EM_ANALISE').subscribe({
      next: (res) => {
        this.pageableAnalises = res
      },
      error(err) {
          console.log(err)
      },
    })
  }

}
