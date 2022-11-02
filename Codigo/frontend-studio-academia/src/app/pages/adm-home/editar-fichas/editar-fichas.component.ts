import { ExerciseService } from 'src/app/services/ExerciseService';
import { Exercicio } from './../../../Models/exercicio';
import { Exercicios } from 'src/app/Models/exercicio';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder, Validators } from '@angular/forms'
import {Ficha, Fichas} from 'src/app/Models/ficha';
import {ActivatedRoute, Router} from '@angular/router';
import { UserFileService } from 'src/app/services/UserFileService';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-editar-fichas',
  templateUrl: './editar-fichas.component.html',
  styleUrls: ['./editar-fichas.component.css']
})
export class EditarFichasComponent implements OnInit {
  idFicha !: number
  nomeFicha !: String
  ficha$ !: Observable<Fichas>
  fichaForm !: FormBuilder | any
  exercicios$ !: Exercicios
  content$ !: pageableObject;
  public userExercises: any;

  constructor(
    private formBuilder: FormBuilder,
    private routeAc : ActivatedRoute,
    private userFileSerce : UserFileService,
    private exerciseService : ExerciseService,
    private userFileService: UserFileService,
    public router: Router) {
  }
  public userId: any;
  public selectedExercice: string = "-1";
  public isToEditUserFileName: boolean = false;
  public newUserFileName: string = "";

  ngOnInit(): void {
    //this.listExercise();

    this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      exercicios: this.formBuilder.array([]) ,
    });

    this.routeAc.params.pipe().subscribe(res => {
      this.userId = res['idAluno']
      this.idFicha = res['idFicha']
      this.nomeFicha = res['nomeFicha']
    })
    this.listExercise();
    this.listarExercicios()
  }

  public editar() : void{
    let body = {
      fileId: this.idFicha,
      fileName: this.nomeFicha,
      fileExercises: this.userExercises
    };
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

  newExercicio(): {} {
    return {
      exercises:{
        exerciseId: -1
      },
      series: 0,
      repetition: 0,
    }
 }

 addExercicio() {
  this.userExercises.push(this.newExercicio());
console.log(this.userExercises)
  let body = {
    exerciseId : this.fichaForm.value,
  }
  // console.log(body)
  //this.cadastrarExercicio(body);
}

removeExercicio(i:number) {
  this.userExercises.splice(i, 1);
}

cadastrarExercicio(body : any){
  this.userFileSerce.addExercises(body).subscribe(
    {
      next: (res) => {
        console.log(res)
      },
      error: (err) => {
        console.log(err)
      }
    }
  )
}

deletarExercicio(idFile : string, idExercise : string ) : void{
  this.userFileSerce.deleteExercise(idFile, idExercise).subscribe(
    {
      next: (res) => {
        console.log(res)
      },
      error: (err) => {
        console.log(err)
      }
    }
  )
}

listaExercicio(){
  this.exerciseService.listByPage(0,10,'').subscribe({
    next : (res =>{
      this.content$ =  res;
      this.exercicios$ =<Exercicios>this.content$.content
    }),
    error : (err)=>{
      console.log(err)
    }
  })
}

listExercise(){
  this.userFileSerce.getExercisesFromList(this.idFicha)
        .subscribe(
          async (res: any) => {
            console.log('===>>> ',res)
            this.userExercises = await res;
          },
    );
}

  returnToList() {
    //todo: rever isso aqui para retornar dependendo da role
     this.router.navigate(['adm/gradeFichas/'+this.userId])
    // if (this.user$?.roles.includes('ADMIN')) {
    //   this.router.navigate(['adm/gradeFichas/'])
    // } else if (this.user$?.roles.includes('ALUNO')) {
    //   this.router.navigate(['aluno'])
    // }
  }

  listarExercicios(){
    this.exerciseService.getExercisesForDropDown().subscribe({
      next: (res)=>{
        this.exercicios$ =<Exercicios>res
      },
      error : (err)=>{
        console.log(err)
      }
    })
  }

  checkValue(i: number, event: any) {
    this.selectedExercice = event?.target?.value
    this.userExercises[i].exercises.exerciseId = Number(event?.target?.value)
  }

  wantsToEditUserFileName() {
    this.isToEditUserFileName = true;
  }

  saveNewUserFileName() {

    let body = {
      idUser: this.userId,
      idFile: this.idFicha,
      fileName: this.newUserFileName
    }
    this.userFileService.edit(body).subscribe(
      {
        next: (res) => {
          console.log(res.fileName)
          this.nomeFicha = res.fileName

        },
        error: (err) => {
          console.log(err)
        },
        complete: () =>{
          this.cancelEditUserFileName()
        }
      }
    )
  }

  cancelEditUserFileName() {
    this.isToEditUserFileName = false;
  }

  updateSeries(i: number, event: any) {
    this.userExercises[i].series = Number(event?.target?.value)
  }
  updateRepetitions(i: number, event: any) {
    this.userExercises[i].repetition = Number(event?.target?.value)
  }
}
