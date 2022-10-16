import {Component, Input, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {UserService} from "../../../services/UserService";

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {

  @Input() isEdit: boolean = false
  novoAlunoForm !: FormGroup
  isAluno: Boolean = false;
  public types = ['ALUNO', 'PROFESSOR', 'NUTRICIONISTA', 'ADMIN']
  public plans = []
  public selectedPlan = ""
  public weekDays = ['SEGUNDA', 'TERÇA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO', 'DOMINGO']

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
      sex: ['',[Validators.required]],
      roles: [[''],[Validators.required]],
      district:['',[Validators.required]],
      plan:[''],
      dueDate: [''],
      weekday: [''],

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
      phone1: this.novoAlunoForm.value["phone"],
      phone2: this.novoAlunoForm.value["phone"],
      birthDate: this.novoAlunoForm.value["birthDate"],
      roles:[ this.novoAlunoForm.value["roles"]],
      sex: this.novoAlunoForm.value["sex"],
      dueDate:this.novoAlunoForm.value["dueDate"],
      plan: this.novoAlunoForm.value["plan"],
      weekday: this.novoAlunoForm.value["weekDay"],
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
            alert("Usuário cadastrado com êxito")
          },
          error: (err) => {
            console.log(err)
            alert("Não foi possível salvar o usuário")
          }
        }
      );
    console.log(novoAluno)
  }

  checkValue(event: any) {
    this.isAluno = event?.target?.value == "ALUNO"

    if(this.isAluno) {
      this.novoAlunoForm.controls["plan"].setValidators([Validators.required])
      this.novoAlunoForm.controls["dueDate"].setValidators([Validators.required])
      this.novoAlunoForm.controls["weekDay"].setValidators([Validators.required])
    }else{
      this.novoAlunoForm.controls["plan"].removeValidators([Validators.required])
      this.novoAlunoForm.controls["dueDate"].removeValidators([Validators.required])
      this.novoAlunoForm.controls["weekDay"].removeValidators([Validators.required])
    }
  }

  public editar() : void{

    let body = {
      email: this.novoAlunoForm.value["email"],
      password: this.novoAlunoForm.value["password"],
      passwordConfirm: this.novoAlunoForm.value["passwordConfirm"],
      name: this.novoAlunoForm.value["name"],
      legalDocument: this.novoAlunoForm.value["legal_document"],
      phone1: this.novoAlunoForm.value["phone"],
      phone2: this.novoAlunoForm.value["phone"],
      birthDate: this.novoAlunoForm.value["birthDate"],
      roles:[ this.novoAlunoForm.value["roles"]],
      sex: this.novoAlunoForm.value["sex"],
      dueDate:this.novoAlunoForm.value["dueDate"],
      plan: this.novoAlunoForm.value["plan"],
      weekday: this.novoAlunoForm.value["weekDay"],
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
}
