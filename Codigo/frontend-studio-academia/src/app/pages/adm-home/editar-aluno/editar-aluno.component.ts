import { Observable } from 'rxjs';
import { Aluno } from './../../../Models/aluno';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/UserService';
import {User} from "../../../Models/user";
import { pageableObject } from 'src/app/Models/PageableObject';



@Component({
  selector: 'app-editar-aluno',
  templateUrl: './editar-aluno.component.html',
  styleUrls: ['./editar-aluno.component.css']
})
export class EditarAlunoComponent implements OnInit {
  idUser !: String
  public user !: User
  aluno$ !: User
  editAlunoForm !: FormGroup
  content$ !: pageableObject

  constructor(
    private formbuilder: FormBuilder,
    private routeAc : ActivatedRoute,
    private userService : UserService,
    private router : Router,
    ) {
      this.routeAc.params.subscribe(params => this.idUser = params['idUser']);


    }

  ngOnInit(): void {
    this.ngBuiltUser( this.idUser);

    this.editAlunoForm = this.formbuilder.group({
      email :  [this.aluno$?.email, [Validators.required, Validators.email]],
      password : [this.aluno$?.password, [Validators.required,Validators.minLength(8)]],
      passwordConfirm : [this.aluno$?.password, [Validators.required,Validators.minLength(8)]],
      name : [this.aluno$?.name,Validators.required],
      legal_document: [this.aluno$?.legal_document,[Validators.required]],
      phone: [this.aluno$?.phone,[Validators.required]],
      birthDate : [this.aluno$?.birthDate,[]],
      zipCode: [this.aluno$?.zipCode,[Validators.required]],
      street: [this.aluno$?.street,[Validators.required]],
      number: [this.aluno$?.number,[]],
      complement: [this.aluno$?.complement,[Validators.required]],
      state: [this.aluno$?.state,[Validators.required]],
      city: [this.aluno$?.city,[Validators.required]],
      sex: [this.aluno$?.sex,[Validators.required]],
      roles: [[this.aluno$?.roles],[Validators.required]],
      district:[this.aluno$?.district,[Validators.required]]

    })
  }

  ngBuiltUser( id : any) {

    console.log('search user by id')
    this.userService.findUser(id)
      .subscribe(
        (res: any) => {
          this.content$ = res;
          this.aluno$ = <User> this.content$?.content
        },
      );
  }

  public editar() : void{

    let body = {
      email: this.editAlunoForm.value["email"],
      password: this.editAlunoForm.value["password"],
      passwordConfirm: this.editAlunoForm.value["passwordConfirm"],
      name: this.editAlunoForm.value["name"],
      legalDocument: this.editAlunoForm.value["legal_document"],
      phone1: this.editAlunoForm.value["phone"],
      phone2: this.editAlunoForm.value["phone"],
      birthDate: this.editAlunoForm.value["birthDate"],
      roles:[ this.editAlunoForm.value["roles"]],
      sex: this.editAlunoForm.value["sex"],
      dueDate:this.user.dueDate,
      plan: this.user.plan,
      weekday: this.user.weekday,
      address: {
        zipCode: this.editAlunoForm.value["zipCode"],
        street: this.editAlunoForm.value["street"],
        number: this.editAlunoForm.value["number"],
        district: this.editAlunoForm.value["district"],
        complement: this.editAlunoForm.value["complement"],
        state: this.editAlunoForm.value["state"],
        city: this.editAlunoForm.value["city"],
      }
    }

    this.userService.edit(body)
      .subscribe(
        {
          next:(res) => {
            console.log(res)
            alert("Usuário editado com êxito")
          },
          error: (err) => {
            console.log(err)
            alert("Não foi possível editar o usuário")
          }
        }
      );

  }



  public delete() : void {
    this.userService.delete(this.idUser)
      .subscribe(
        {
          next:(res) => {
            console.log(res)
            alert("Usuário apagado com êxito")
            this.router.navigateByUrl('adm/home')
          },
          error: (err) => {
            console.log(err)
            alert("Não foi possível deletar o usuário")
          }
        }
      );
  }
}
