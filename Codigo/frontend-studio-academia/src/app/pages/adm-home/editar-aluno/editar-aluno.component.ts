import { Observable } from 'rxjs';
import { Aluno } from './../../../Models/aluno';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/UserService';
import {User} from "../../../Models/user";

@Component({
  selector: 'app-editar-aluno',
  templateUrl: './editar-aluno.component.html',
  styleUrls: ['./editar-aluno.component.css']
})
export class EditarAlunoComponent implements OnInit {
  idAluno !: String
  user !: User
  aluno$ !: Observable<User>
  editAlunoForm !: FormGroup

  constructor(
    private formbuilder: FormBuilder,
    private routeAc : ActivatedRoute,
    private userService : UserService,
    private router : Router,
    ) {
      this.routeAc.params.subscribe(params => this.user = params['user']);
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
    this.userService.delete(this.idAluno)
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
