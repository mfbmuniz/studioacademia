import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PlanService } from 'src/app/services/planService';

@Component({
  selector: 'app-editar-plano',
  templateUrl: './editar-plano.component.html',
  styleUrls: ['./editar-plano.component.css']
})
export class EditarPlanoComponent implements OnInit {
  planForm !: FormGroup
  planId !: number
  content !: any

  constructor(
    private formBuilder : FormBuilder,
    private planService : PlanService,
    private toastr: ToastrService,
    private routeAc : ActivatedRoute,) {
      this.routeAc.params.subscribe(params => this.planId = params['planId']);
      this.ngBuiltPlan(this.planId)
    }

  ngOnInit(): void {
    this.planForm = this.formBuilder.group({
      planCode : [[''],Validators.required],
      name : [[''],Validators.required],
      price : [[''],Validators.required],
      description : [[''],Validators.required],
      contractedDays : [[''],Validators.required],
    })
  }
  ngBuiltPlan( id : any) {

    console.log('search Exercise by id')
    this.planService.getPlanById(id)
      .subscribe(
        {
          next: (res) =>{
            this.content = res;
            }, error: (err) => {
            console.log(err)
          },complete: () =>{
            this.completeFields();
          }
        });
  }

  editar(){
    let body = {
      name : this.planForm.value['name'],
      price : this.planForm.value['price'],
      description: this.planForm.value['description'],
      planCode : this.planForm.value['planCode'],
      contractedDays : this.planForm.value['contractedDays'],
    }

    console.log(body)
    this.planService.update(body).subscribe({
      next : (res)=>{
        this.showSuccessToastr()
      },
      error : (err) =>{
        console.log(err)
        this.showErrorToastr()
      }
    })

  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

  private completeFields() {
    this.planForm = this.formBuilder.group({
      planCode : [this.content?.planConde,Validators.required,],
      name : [this.content?.name,Validators.required],
      price : [this.content?.price,Validators.required],
      description : [this.content?.description,Validators.required],
      contractedDays : [this.content?.contractedDays,Validators.required],
    })
  }

}
