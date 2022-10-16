import {Component, Input, OnInit} from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder, Validators } from '@angular/forms'
import {ActivatedRoute, Router} from '@angular/router';
import { UserFileService } from 'src/app/services/UserFileService';
import {UserService} from "../../../services/UserService";
import {ExerciseService} from "../../../services/ExerciseService";
import {Fichas} from "../../../Models/ficha";
import {pageableObject} from "../../../Models/PageableObject";
import {Alunos} from "../../../Models/aluno";

@Component({
  selector: 'app-cadastro-ficha',
  templateUrl: './cadastro-ficha.component.html',
  styleUrls: ['./cadastro-ficha.component.css']
})
export class CadastroFichaComponent implements OnInit {
  emailAluno !:  String
  fichaForm !: FormBuilder | any

  @Input() fichas !: Fichas
  @Input() pageable !: pageableObject

  constructor(
    private formBuilder: FormBuilder,
    private routeAc : ActivatedRoute,

  ) {

    this.routeAc.params.subscribe(params => this.emailAluno = params['idAluno']);

    this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      description : '',
      exercicios: this.formBuilder.array([]) ,
    })

  }


  ngOnInit(): void {



  }

  public cadastrar() : void{

    let body = {
      user_id : this.emailAluno,
      name : this.fichaForm.value["name"],
    }

  }

  get exercicios() : FormArray {
    return this.fichaForm.get("exercicios") as FormArray
  }

  newExercicio(): FormGroup {
    return this.formBuilder.group({
      exercicio: '',
      serie: ['',Validators.min(0)],
      repeticoes: '',
    })
 }

   addExercicio() {
    this.exercicios.push(this.newExercicio());
  }


  removeExercicio(i:number) {
    this.exercicios.removeAt(i);
  }

}
