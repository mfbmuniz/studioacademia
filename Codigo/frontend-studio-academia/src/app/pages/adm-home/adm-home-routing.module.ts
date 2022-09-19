import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmHomeComponent } from './adm-home.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';
import {AuthGuard} from "../../shared/guards/AuthGuard";
import { CadastroExercicioComponent } from './cadastro-exercicio/cadastro-exercicio.component';

const routes: Routes = [
  {
    path: '',
    component: AdmHomeComponent,
    children:[
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'adm/cadastrarUsuario'
      },
      {
        path: 'cadastrarUsuario',
        component: CadastroUsuarioComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cadastrarAluno',
        component: CadastroAlunoComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cadastrarExercicio',
        component: CadastroExercicioComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdmHomeRoutingModule { }
