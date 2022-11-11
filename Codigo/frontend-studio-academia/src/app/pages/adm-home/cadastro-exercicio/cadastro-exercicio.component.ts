import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import {UserService} from "../../../services/UserService";
import {ExerciseService} from "../../../services/ExerciseService";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastro-exercicio',
  templateUrl: './cadastro-exercicio.component.html',
  styleUrls: ['./cadastro-exercicio.component.css']
})
export class CadastroExercicioComponent implements OnInit {

  novoExercicioForm !: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private router : Router,
    private exerciseRegistrationService: ExerciseService,
    private toastr: ToastrService,
    ) { }



  ngOnInit(): void {
    this.novoExercicioForm = this.formBuilder.group({
      name : ['',Validators.required,],
      url : ['',Validators.required],
      description : ['',Validators.required]
    })
  }

    cadastrar() : void{
    const novoExercicio = this.novoExercicioForm.getRawValue();
    console.log("AQUI")
    let body = {
      description : this.novoExercicioForm.value["description"],
      exerciseUrl : this.novoExercicioForm.value["url"],
      name : this.novoExercicioForm.value["name"],
    }

    this.exerciseRegistrationService.create(body)
      .subscribe(
        {
          next:(res) => {
            console.log(res)
            this.novoExercicioForm.reset()
            this.showSuccessToastr()
          },
          error: (err) => {
            console.log(err)
            this.showSuccessToastr();
          }
        }
      );
    console.log(novoExercicio)

  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

}
/**
 * let body = {
      email: this.novoAlunoForm.value["email"],
      password: this.novoAlunoForm.value["password"],
      passwordConfirm: this.novoAlunoForm.value["passwordConfirm"],
      name: this.novoAlunoForm.value["name"],
      legalDocument: this.novoAlunoForm.value["legal_document"],
      phone: this.novoAlunoForm.value["phone"],
      birthDate: this.novoAlunoForm.value["birthDate"],
      roles: this.novoAlunoForm.value["roles"],
      sex: this.novoAlunoForm.value["sex"],
      address: {
        zipCode: this.novoAlunoForm.value["zipCode"],
        street: this.novoAlunoForm.value["street"],
        number: this.novoAlunoForm.value["number"],
        district: this.novoAlunoForm.value["district"],
        complement: this.novoAlunoForm.value["complement"],
        state: this.novoAlunoForm.value["state"],
        city: this.novoAlunoForm.value["city"],
      }
 */
