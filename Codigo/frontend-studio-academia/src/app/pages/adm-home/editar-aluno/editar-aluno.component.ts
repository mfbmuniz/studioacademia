import { Observable } from 'rxjs';
import { Aluno } from './../../../Models/aluno';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-editar-aluno',
  templateUrl: './editar-aluno.component.html',
  styleUrls: ['./editar-aluno.component.css']
})
export class EditarAlunoComponent implements OnInit {

  aluno$ !: Observable<Aluno>
  editAlunoForm !: FormGroup

  constructor(
    private formbuilder: FormBuilder
    ) { }

  ngOnInit(): void {

    //this.aluno$ = --> Faz o select

    this.editAlunoForm = this.formbuilder.group({
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

    })
  }

  public editar() : void{

  }

}
