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
      legalDocument: ['',[Validators.required]],
      phone: ['',[Validators.required]],
      birthDate : ['',[]],
      zipCode: ['',[Validators.required]],
      street: ['',[Validators.required]],
      number: ['',[]],
      complement: ['',[Validators.required]],
      state: ['',[Validators.required]],
      city: ['',[Validators.required]]
    }
    );
  }

  cadastrar(){
    const novoAluno = this.novoAlunoForm.getRawValue();

    this.userService.create(novoAluno)
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
