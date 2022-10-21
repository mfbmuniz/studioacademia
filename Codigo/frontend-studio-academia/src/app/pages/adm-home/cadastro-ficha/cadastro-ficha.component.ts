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
  idAluno !:  String
  idFicha !: String
  fichaForm !: FormBuilder | any

  fichas !: Fichas


  constructor(
    private formBuilder: FormBuilder,
    private routeAc : ActivatedRoute,
    private userFileService : UserFileService

  ) {

    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno']);
    this.routeAc.params.subscribe(params => this.idFicha = params['ifFicha']);

    this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      description : '',
      exercicios: this.formBuilder.array([]) ,
    })

  }


  ngOnInit(): void {



  }

  public cadastrar() : void{

    let body = this.fichaForm.value
    console.log(body)
    this.userFileService.addExercises(body).subscribe({
      next : (res) =>{
        console.log(res)
        alert("Exercícios cadastrados com sucesso")
      },
      error(err) {
          console.log(err)
          alert("Não foi possível cadastrar exercícios")
      },
    })
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
