import { Component, OnInit } from '@angular/core';
import { PageableObject } from 'src/app/Models/PageableObject';
import { Plans } from 'src/app/Models/plan';
import { PlanService } from 'src/app/services/planService';

@Component({
  selector: 'app-planos',
  templateUrl: './planos.component.html',
  styleUrls: ['./planos.component.css']
})
export class PlanosComponent implements OnInit {

  planos !: Plans
  pageable !: PageableObject

  constructor(private planService : PlanService) { }

  ngOnInit(): void {
    this.listPlanos(0,10)
  }

  listPlanos(page : number, size : number){
    this.planService.listPlans(page,size).subscribe({
      next: (res)=>{
        this.pageable = res
        this.planos = this.pageable?.content as Plans

      },
      error(err) {
          console.log(err)
      },
    })
  }

}
