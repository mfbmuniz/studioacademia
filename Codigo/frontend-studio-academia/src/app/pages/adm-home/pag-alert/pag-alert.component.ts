import { Component, OnInit } from '@angular/core';
import { PageableObject } from 'src/app/Models/PageableObject';
import { MonthlyPaymentService } from 'src/app/services/monthly-paymentService';

@Component({
  selector: 'app-pag-alert',
  templateUrl: './pag-alert.component.html',
  styleUrls: ['./pag-alert.component.css']
})
export class PagAlertComponent implements OnInit {

  pageable_Atradados$ !: PageableObject

  constructor(private payService : MonthlyPaymentService) { }

  ngOnInit(): void {
    this.pageAllPayAtrasados()
  }

  pageAllPayAtrasados(){
    this.payService.pageAllPendencyByKeySearch(0,10,"ATRASADO").subscribe({
      next : (res)=>{
        this.pageable_Atradados$ = res

      },
      error(err) {
          console.log(err)
      },
    })
  }

}
