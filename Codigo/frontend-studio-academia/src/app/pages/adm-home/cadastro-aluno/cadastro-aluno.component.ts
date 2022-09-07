import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {

  novoAlunoForm !: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.novoAlunoForm = this.formBuilder.group({
      email :  ['', [Validators.required, Validators.email]],
      senha : ['', [Validators.required,Validators.minLength(8)]],
      nome : ['',Validators.required],
      cpf: ['',[Validators.required]],
      telefone: ['',[Validators.required]],
      dataNascimento : ['',[]],
      cep: ['',[Validators.required]],
      logradouro: ['',[Validators.required]],
      numero: ['',[]],
      complemento: ['',[Validators.required]],
      estado: ['',[Validators.required]],
      cidade: ['',[Validators.required]]
    }
    )
  }

  cadastrar(){
    const novoAluno = this.novoAlunoForm.getRawValue()
    console.log(novoAluno)
  }

}
