import { GradeExerciciosComponent } from './grade-exercicios/grade-exercicios.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunoHomeComponent } from './aluno-home.component';
import { GradeFichasComponent } from './grade-fichas/grade-fichas.component';
import { GradePendenciasComponent } from './grade-pendencias/grade-pendencias.component';

const routes: Routes = [
  {
    path: '',
    component: AlunoHomeComponent,
    children:[

      {
        path: 'fichas/:id',
        component: GradeFichasComponent
      },
      {
        path: 'exercicios/:idFile',
        component: GradeExerciciosComponent
      },
      {
        path: 'pagamentos',
        component : GradePendenciasComponent
      }

    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunoHomeRoutingModule { }
