import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent implements OnInit {

  novoUsuarioForm !: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.novoUsuarioForm = this.formBuilder.group({
      email :  ['', [Validators.required, Validators.email]],
      senha : ['', [Validators.required,Validators.minLength(8)]],
      origem : ['', []],
      nome : ['',Validators.required],
      cpf: ['',[Validators.required]],
      telefone: ['',[Validators.required]]
    }
    )
  }

  public cadastrar():void{
    if(this.novoUsuarioForm.valid){
      const novoUsuario = this.novoUsuarioForm.getRawValue();
      console.log(novoUsuario)
    }
  }

}
