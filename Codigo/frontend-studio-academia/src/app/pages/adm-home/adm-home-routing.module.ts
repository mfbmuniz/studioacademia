import { CadastroPlanoComponent } from './cadastro-plano/cadastro-plano.component';
import { CommentsComponent } from './comments/comments.component';
import { EditarExercicioComponent } from './editar-exercicio/editar-exercicio.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmHomeComponent } from './adm-home.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import {AuthGuard} from "../../shared/guards/AuthGuard";
import { CadastroExercicioComponent } from './cadastro-exercicio/cadastro-exercicio.component';
import { SearchHomeComponent } from './search-home/search-home.component';
import { CadastroFichaComponent } from './cadastro-ficha/cadastro-ficha.component';
import { EditarAlunoComponent } from './editar-aluno/editar-aluno.component';
import { AdmGradeFichasComponent } from './adm-grade-fichas/adm-grade-fichas.component';
import { EditarFichasComponent } from './editar-fichas/editar-fichas.component';
import { GradeAlunoPagamentosComponent } from './grade-aluno-pagamentos/grade-aluno-pagamentos.component';
import { GradeGeralPagamentosComponent } from './grade-geral-pagamentos/grade-geral-pagamentos.component';


const routes: Routes = [
  {
    path: '',
    component: AdmHomeComponent,
    children:[
      {
        path: '',
        component: SearchHomeComponent
      },
      {
        path: 'cadastrarUsuario',
        component: CadastroAlunoComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cadastrarExercicio',
        component: CadastroExercicioComponent
      },
      {
        path: 'editarExercicio/:idExercicio',
        component: EditarExercicioComponent
      },
      {
        path: 'editarAluno/:idUser',
        component: EditarAlunoComponent
      },
      {
        path: 'gradeFichas/:idAluno',
        component: AdmGradeFichasComponent
      },
      {
        path : 'adicionarExercicios/:idAluno/:idFicha',
        component : CadastroFichaComponent
      },
      {
        path: 'editarFichas/:idAluno/:idFicha/:nomeFicha',
        component: EditarFichasComponent
      },
      {
        path: 'expandirFicha/:idFicha',
        component: AdmGradeFichasComponent
      },
      {
        path : 'pagamentos/:idAluno',
        component : GradeAlunoPagamentosComponent
      },
      {
        path : 'visualizacaoPagamentos',
        component : GradeGeralPagamentosComponent
      },
      {
        path : 'comentarios',
        component : CommentsComponent
      },
      {
        path : 'cadastrarPlanos',
        component : CadastroPlanoComponent
      },

    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdmHomeRoutingModule { }
