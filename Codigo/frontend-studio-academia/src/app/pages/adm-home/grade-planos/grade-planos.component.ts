import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { PageableObject } from 'src/app/Models/PageableObject';
import { Plans } from 'src/app/Models/plan';
import { PlanService } from 'src/app/services/planService';

@Component({
  selector: 'app-grade-planos',
  templateUrl: './grade-planos.component.html',
  styleUrls: ['./grade-planos.component.css']
})
export class GradePlanosComponent implements OnInit {

  planos !: Plans
  pageable$ !: PageableObject

  constructor(private planService: PlanService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.listPlans(0, 10)
  }


  listPlans(page: number, size: number,) {
    this.planService.listPlans(page, size).subscribe({
      next: (res) => {
        this.pageable$ = res,
        this.planos = this.pageable$?.content as Plans
      },
      error: (err) => {
        console.log(err)
      }
    })
  }



  deletePlan(planId: number) {
    this.planService.delete(planId).subscribe({
      next: (res) =>{
        console.log(res)
        this.showSuccessToastr()
      },
      error : (err)=>{
        console.log(err)
        this.showErrorToastr()
      }
    })

  }

  showSuccessToastr() {
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr() {
    this.toastr.error("O envio não pode ser feito", "Erro")
  }
}
