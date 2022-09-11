import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {UserService} from "../../../services/UserService";

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {

  novoAlunoForm !: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private router : Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.novoAlunoForm = this.formBuilder.group({
      email :  ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required,Validators.minLength(8)]],
      passwordConfirm : ['', [Validators.required,Validators.minLength(8)]],
      name : ['',Validators.required],
      legal_document: ['',[Validators.required]],
      phone: ['',[Validators.required]],
      birthDate : ['',[]],
      zipCode: ['',[Validators.required]],
      street: ['',[Validators.required]],
      number: ['',[]],
      complement: ['',[Validators.required]],
      state: ['',[Validators.required]],
      city: ['',[Validators.required]],
      sex: ['M',[Validators.required]],
      roles: [['ALUNO'],[Validators.required]],
      district:['Durval de Barros',[Validators.required]]
    }
    );
  }

  cadastrar(){
    const novoAluno = this.novoAlunoForm.getRawValue();

    let body = {
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
    }

    this.userService.create(body)
      .subscribe(
        {
          next:(res) => {
            console.log(res)
          },
          error: (err) => {
            console.log(err)
          }
        }
      );
    console.log(novoAluno)
  }

}
