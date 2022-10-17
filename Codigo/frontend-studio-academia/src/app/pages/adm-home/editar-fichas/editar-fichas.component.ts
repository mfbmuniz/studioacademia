import { ExerciseService } from 'src/app/services/ExerciseService';
import { Exercicio } from './../../../Models/exercicio';
import { Exercicios } from 'src/app/Models/exercicio';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder, Validators } from '@angular/forms'
import {Ficha, Fichas} from 'src/app/Models/ficha';
import { ActivatedRoute } from '@angular/router';
import { UserFileService } from 'src/app/services/UserFileService';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-editar-fichas',
  templateUrl: './editar-fichas.component.html',
  styleUrls: ['./editar-fichas.component.css']
})
export class EditarFichasComponent implements OnInit {
  idFicha !: String
  nomeFicha !: String
  ficha$ !: Observable<Fichas>
  fichaForm !: FormBuilder | any
  exercicios$ !: Exercicios
  content$ !: pageableObject;

  constructor(
    private formBuilder: FormBuilder,
    private routeAc : ActivatedRoute,
    private userFileSerce : UserFileService,
    private exerciseService : ExerciseService) {
      this.routeAc.params.subscribe(params => this.idFicha = params['idFicha'])
      this.routeAc.params.subscribe(params => this.nomeFicha = params['nomeFicha']);
      this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      exercicios: this.formBuilder.array([]) ,
    })
  }

  ngOnInit(): void {
    this.listExercise();
  }

  public editar() : void{
    console.log(this.fichaForm.value);
  }

  get exercicios() : FormArray {
    return this.fichaForm.get("exercicios") as FormArray
  }

  newExercicio(): FormGroup {
    return this.formBuilder.group({
      exerciseId: ['', Validators.required],
      series: ['',Validators.min(0)],
      repetitions: ['',Validators.required],
    })
 }

 addExercicio() {
  this.exercicios.push(this.newExercicio());

  //Não to conseguindo pegar os dados q eu quero
  let body = {
    exerciseId : this.fichaForm.value,

  }
  console.log(body)
  //this.cadastrarExercicio(body);
}

removeExercicio(i:number) {
  this.exercicios.removeAt(i);
}

cadastrarExercicio(body : any){
  this.userFileSerce.addExercise(body).subscribe(
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

listExercise(){
  this.userFileSerce.listUserExercisesByPageWithSize(0,10,this.idFicha)
        .subscribe(
          async (res: any) => {
            this.content$ = await res;
            this.exercicios$ =<Exercicios>this.content$.content
          },
    );
}

}
