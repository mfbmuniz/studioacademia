import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder, Validators } from '@angular/forms'
import { Ficha } from 'src/app/Models/ficha';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editar-fichas',
  templateUrl: './editar-fichas.component.html',
  styleUrls: ['./editar-fichas.component.css']
})
export class EditarFichasComponent implements OnInit {
  idFicha !: String
  ficha$ !: Observable<Ficha>
  fichaForm !: FormBuilder | any

  constructor(
    private formBuilder: FormBuilder,
    private routeAc : ActivatedRoute) {
      this.routeAc.params.subscribe(params => this.idFicha = params['idFicha']);
    this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      description : '',
      exercicios: this.formBuilder.array([]) ,
    })
  }

  ngOnInit(): void {
  }

  public editar() : void{
    console.log(this.fichaForm.value);
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
