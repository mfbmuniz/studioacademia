import { Observable } from 'rxjs';
import { Aluno } from './../../../Models/aluno';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editar-aluno',
  templateUrl: './editar-aluno.component.html',
  styleUrls: ['./editar-aluno.component.css']
})
export class EditarAlunoComponent implements OnInit {
  emailAluno !: String
  aluno$ !: Observable<Aluno>
  editAlunoForm !: FormGroup

  constructor(
    private formbuilder: FormBuilder,
    private routeAc : ActivatedRoute
    ) {
      this.routeAc.params.subscribe(params => this.emailAluno = params['idAluno']);
    }

  ngOnInit(): void {

    //this.aluno$ = --> Faz o select
    var pegarValorTacaNoForm = 'Valor no bd'
    var teste = 'teste'
    this.editAlunoForm = this.formbuilder.group({
      email :  ['pegarValorTacaNoForm', [Validators.required, Validators.email]],
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
      sex: ['',[Validators.required]],
      roles: [[''],[Validators.required]],
      district:['',[Validators.required]]

    })
  }

  public editar() : void{

  }

  public delete() : void {
    alert()
  }
}
