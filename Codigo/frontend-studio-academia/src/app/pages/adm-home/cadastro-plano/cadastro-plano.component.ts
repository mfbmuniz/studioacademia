import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PlanService } from 'src/app/services/planService';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastro-plano',
  templateUrl: './cadastro-plano.component.html',
  styleUrls: ['./cadastro-plano.component.css']
})
export class CadastroPlanoComponent implements OnInit {

  planForm !: FormGroup

  constructor(
    private formBuilder : FormBuilder,
    private planService : PlanService,
    private toastr: ToastrService,) { }

  ngOnInit(): void {
    this.planForm = this.formBuilder.group({
      planCode : [[''],Validators.required],
      name : [[''],Validators.required],
      price : [[''],Validators.required],
      description : [[''],Validators.required],
      contractedDays : [[''],Validators.required],
    })
  }

  cadastrar(){
    let body = {
      name : this.planForm.value['name'],
      price : this.planForm.value['price'],
      description: this.planForm.value['description'],
      planCode : this.planForm.value['planCode'],
      contractedDays : this.planForm.value['contractedDays'],
    }

    console.log(body)
    this.planService.create(body).subscribe({
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

}
