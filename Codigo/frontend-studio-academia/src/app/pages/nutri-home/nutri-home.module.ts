import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NutriHomeRoutingModule } from './nutri-home-routing.module';
import { NutriHomeComponent } from './nutri-home.component';
import { NutriSidebarComponent } from './nutri-sidebar/nutri-sidebar.component';
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule } from '@angular/forms';
import { GradeAlunosComponent } from './grade-alunos/grade-alunos.component';
import { AvalicaoFisicaComponent } from './avalicao-fisica/avalicao-fisica.component';
import { MensagemModule } from 'src/app/components/mensagem/mensagem.module';


@NgModule({
  declarations: [
    NutriHomeComponent,
    NutriSidebarComponent,
    SearchComponent,
    GradeAlunosComponent,
    AvalicaoFisicaComponent,

  ],
  imports: [
    CommonModule,
    NutriHomeRoutingModule,
    ReactiveFormsModule,
    MensagemModule,
  ],
  exports : [
    NutriHomeComponent
  ]
})
export class NutriHomeModule { }
