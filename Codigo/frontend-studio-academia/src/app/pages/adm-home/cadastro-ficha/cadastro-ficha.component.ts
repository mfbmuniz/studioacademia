import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,FormArray, FormBuilder, Validators } from '@angular/forms'

@Component({
  selector: 'app-cadastro-ficha',
  templateUrl: './cadastro-ficha.component.html',
  styleUrls: ['./cadastro-ficha.component.css']
})
export class CadastroFichaComponent implements OnInit {

  fichaForm !: FormBuilder | any

  constructor(
    private formBuilder: FormBuilder
  ) {
    this.fichaForm = this.formBuilder.group({
      name: ['',Validators.required],
      description : '',
      exercicios: this.formBuilder.array([]) ,
    })
  }

  ngOnInit(): void {
  }

  public cadastrar() : void{
    console.log(this.fichaForm.value);console.log
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
