import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlunoHomeRoutingModule } from './aluno-home-routing.module';
import { AlunoHomeComponent } from './aluno-home.component';
import { SidebarModule } from 'src/app/components/sidebar/sidebar.module';
import { GradeFichasComponent } from './grade-fichas/grade-fichas.component';
import { AlunoSidebarComponent } from './aluno-sidebar/aluno-sidebar.component';


@NgModule({
  declarations: [
    AlunoHomeComponent,
    GradeFichasComponent,
    AlunoSidebarComponent
  ],
  imports: [
    CommonModule,
    AlunoHomeRoutingModule,
  ],
  exports: [
    AlunoHomeComponent
  ]
})
export class AlunoHomeModule { }
