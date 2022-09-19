import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-exercicio',
  templateUrl: './cadastro-exercicio.component.html',
  styleUrls: ['./cadastro-exercicio.component.css']
})
export class CadastroExercicioComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder
    ) { }

  novoExercicioForm !: FormGroup
  ngOnInit(): void {
    this.novoExercicioForm = this.formBuilder.group({
      name : ['',Validators.required,],
      url : ['',Validators.required],
      description : ['',]
    })
  }

  public cadastrar() : void{

  }

}
