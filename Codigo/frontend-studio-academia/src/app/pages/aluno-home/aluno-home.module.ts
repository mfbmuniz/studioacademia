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




@NgModule({
  declarations: [
    AlunoHomeComponent,
    GradeFichasComponent,
    AlunoSidebarComponent,
    GradeExerciciosComponent,
    GradePendenciasComponent
  ],
  imports: [
    CommonModule,
    AlunoHomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    AlunoHomeComponent
  ]
})
export class AlunoHomeModule { }
