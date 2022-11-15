import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NutriHomeRoutingModule } from './nutri-home-routing.module';
import { NutriHomeComponent } from './nutri-home.component';
import { NutriSidebarComponent } from './nutri-sidebar/nutri-sidebar.component';
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule } from '@angular/forms';
import { GradeAlunosComponent } from './grade-alunos/grade-alunos.component';


@NgModule({
  declarations: [
    NutriHomeComponent,
    NutriSidebarComponent,
    SearchComponent,
    GradeAlunosComponent,

  ],
  imports: [
    CommonModule,
    NutriHomeRoutingModule,
    ReactiveFormsModule,
  ],
  exports : [
    NutriHomeComponent
  ]
})
export class NutriHomeModule { }
