import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmHomeComponent } from './adm-home.component';
import { AdmSidebarComponent } from './adm-sidebar/adm-sidebar.component';
import { CadastroAlunoComponent } from './cadastro-aluno/cadastro-aluno.component';
import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';

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
        component: CadastroUsuarioComponent
      },
      {
        path: 'cadastrarAluno',
        component: CadastroAlunoComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdmHomeRoutingModule { }
