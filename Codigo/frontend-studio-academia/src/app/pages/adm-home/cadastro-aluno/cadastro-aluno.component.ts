import { ToastrService } from 'ngx-toastr';
import {Component, Input, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from "../../../services/UserService";
import {User} from "../../../Models/user";


@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.css']
})
export class CadastroAlunoComponent implements OnInit {

  @Input() isEdit: boolean = false
  @Input() content$ !: User
  nomeTitulo : any
  novoAlunoForm !: FormGroup
  isAluno: Boolean = false;
  public types = ['ALUNO', 'PROFESSOR', 'NUTRICIONISTA', 'ADMIN']
  public plans = []
  public selectedPlan = ""
  public weekDays = ['SEGUNDA', 'TERÇA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO', 'DOMINGO']
  idUser !: String
  user !: User
  userForm !: any


  constructor(
    private formBuilder: FormBuilder,
    private router : Router,
    private userService: UserService,
    private toastr: ToastrService,
    private routeAc : ActivatedRoute,
  ) {
    this.routeAc.params.subscribe(params => this.idUser = params['idUser']);



  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

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
        roles: [[],[Validators.required]],
        district:['',[Validators.required]],
        plan:[''],
        dueDate: [''],
        weekday: [[],[]],

      }
    );

    if (this.isEdit) {
      console.log('veja bem')
      this.nomeTitulo= "Editar Usuario"
      this.ngBuiltUser(this.idUser);

    }else{
      this.nomeTitulo= "Cadastrar Usuario"
    }
  }

  ngBuiltUser( id : any) {
    console.log('chamei pelo cadastro')
    console.log('search user by id: ')
    console.log(id)
    this.userService.findUser(id)
      .subscribe(
        {
          next: (res) => {
            this.userForm = res;
          }, error: (err) => {
            console.log(err)
          }, complete: () => {
            this.completeFields();
          }
        }

      );

  }

  completeFields(){

    this.isAluno = this.userForm?.roles[0]?.name == "ALUNO";
    this.checkValueModular();

    if(this.isAluno) {
      this.novoAlunoForm = this.formBuilder.group({
          email: [this.userForm?.email, [Validators.required, Validators.email]],
          password: ['', [Validators.required, Validators.minLength(8)]],
          passwordConfirm: ['', [Validators.required, Validators.minLength(8)]],
          name: [this.userForm?.name, Validators.required],
          legal_document: [this.userForm?.legal_document, [Validators.required]],
          phone: [this.userForm?.phone1, [Validators.required]],
          birthDate: [(this.userForm?.birthDate).substring(0, 10), []],
          zipCode: [this.userForm?.address?.zipCode, [Validators.required]],
          street: [this.userForm?.address?.street, [Validators.required]],
          number: [this.userForm?.address?.number, []],
          complement: [this.userForm?.address?.complement, [Validators.required]],
          state: [this.userForm?.address?.state?.uf, [Validators.required]],
          city: [this.userForm?.address?.city?.city, [Validators.required]],
          sex: [this.userForm?.sex, [Validators.required]],
          roles: [this.userForm?.roles[0]?.name, [Validators.required]],
          district: [this.userForm?.address?.district, [Validators.required]],
          plan: [this.userForm?.plan, []],
          dueDate: [(this.userForm?.dueDate).substring(0, 10), []],
          weekday: [this.userForm.weekday, []],

        }
      );
    }else{
      this.novoAlunoForm = this.formBuilder.group({
        email: [this.userForm?.email, [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]],
        passwordConfirm: ['', [Validators.required, Validators.minLength(8)]],
        name: [this.userForm?.name, Validators.required],
        legal_document: [this.userForm?.legal_document, [Validators.required]],
        phone: [this.userForm?.phone1, [Validators.required]],
        birthDate: [(this.userForm?.birthDate).substring(0, 10), []],
        zipCode: [this.userForm?.address?.zipCode, [Validators.required]],
        street: [this.userForm?.address?.street, [Validators.required]],
        number: [this.userForm?.address?.number, []],
        complement: [this.userForm?.address?.complement, [Validators.required]],
        state: [this.userForm?.address?.state?.uf, [Validators.required]],
        city: [this.userForm?.address?.city?.city, [Validators.required]],
        sex: [this.userForm?.sex, [Validators.required]],
        roles: [this.userForm?.roles[0]?.name, [Validators.required]],
        district: [this.userForm?.address?.district, [Validators.required]],
        }
      );
    }



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
      weekDays:  this.novoAlunoForm.value["weekday"],
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
            this.novoAlunoForm.reset()
            this.showSuccessToastr()
          },
          error: (err) => {
            console.log(err)
            this.showErrorToastr()
          }
        }
      );
    console.log(novoAluno)
  }


  checkValueModular(){


    if(this.isAluno) {
      this.novoAlunoForm.controls["plan"].setValidators([Validators.required])
      this.novoAlunoForm.controls["dueDate"].setValidators([Validators.required])
      this.novoAlunoForm.controls["weekday"].setValidators([])
    }else{
      this.novoAlunoForm.controls["plan"].removeValidators([Validators.required])
      this.novoAlunoForm.controls["dueDate"].removeValidators([Validators.required])
      this.novoAlunoForm.controls["weekday"].removeValidators([Validators.required])
    }
  }

  checkValue(event: any) {

    this.isAluno = event?.target?.value == "ALUNO";
    this.checkValueModular();
  }

  public editar() : void{

    this.user=this.content$;
    console.log('entrou no editar do cadastro e me mostra o user');
    console.log(this.content$);
    console.log(this.user);


    let body = {
      idUser:this.user.idUser,
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
      plan:this.novoAlunoForm.value["plan"],
      weekDays: this.novoAlunoForm.value["weekday"],
      address: {
        addressId:this.userForm?.address?.addressId,
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
            this.showSuccessToastr()
          },
          error: (err) => {
            console.log(err)
            this.showErrorToastr()
          }
        }
      );

  }
}
