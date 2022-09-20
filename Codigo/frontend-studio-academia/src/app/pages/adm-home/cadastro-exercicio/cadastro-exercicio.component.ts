import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import {UserService} from "../../../services/UserService";
import {ExerciseService} from "../../../services/ExerciseService";

@Component({
  selector: 'app-cadastro-exercicio',
  templateUrl: './cadastro-exercicio.component.html',
  styleUrls: ['./cadastro-exercicio.component.css']
})
export class CadastroExercicioComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router : Router,
    private exerciseRegistrationService: ExerciseService,
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
