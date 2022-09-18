import { MensagemModule } from './../../components/mensagem/mensagem.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdmHomeRoutingModule } from './adm-home-routing.module';
import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdmHomeComponent } from './adm-home.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import { AdmSidebarComponent } from './adm-sidebar/adm-sidebar.component';
import { SidebarModule } from 'src/app/components/sidebar/sidebar.module';



@NgModule({
  declarations: [
    AdmHomeComponent,
    CadastroUsuarioComponent,
    CadastroAlunoComponent,
    AdmSidebarComponent
  ],
  imports: [
    CommonModule,
    AdmHomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MensagemModule,
    SidebarModule
  ],
  exports:[
    AdmHomeComponent
  ]
})
export class AdmHomeModule { }
