import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdmHomeRoutingModule } from './adm-home-routing.module';
import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';



@NgModule({
  declarations: [

  
    CadastroUsuarioComponent
  ],
  imports: [
    CommonModule,
    AdmHomeRoutingModule
  ]
})
export class AdmHomeModule { }
