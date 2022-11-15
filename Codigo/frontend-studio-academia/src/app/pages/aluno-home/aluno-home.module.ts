import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlunoHomeRoutingModule } from './aluno-home-routing.module';
import { AlunoHomeComponent } from './aluno-home.component';
import { SidebarModule } from 'src/app/components/sidebar/sidebar.module';
import { GradeFichasComponent } from './grade-fichas/grade-fichas.component';
import { AlunoSidebarComponent } from './aluno-sidebar/aluno-sidebar.component';
import { GradeExerciciosComponent } from './grade-exercicios/grade-exercicios.component';
import { GradePendenciasComponent } from './grade-pendencias/grade-pendencias.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommentsComponent } from './comments/comments.component';
import { MensagemModule } from 'src/app/components/mensagem/mensagem.module';
import { PagAlertComponent } from './pag-alert/pag-alert.component';
import { AlertModule } from 'src/app/components/alert/alert.module';




@NgModule({
  declarations: [
    AlunoHomeComponent,
    GradeFichasComponent,
    AlunoSidebarComponent,
    GradeExerciciosComponent,
    GradePendenciasComponent,
    CommentsComponent,
    PagAlertComponent
  ],
  imports: [
    CommonModule,
    AlunoHomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MensagemModule,
    AlertModule
  ],
  exports: [
    AlunoHomeComponent
  ]
})
export class AlunoHomeModule { }
