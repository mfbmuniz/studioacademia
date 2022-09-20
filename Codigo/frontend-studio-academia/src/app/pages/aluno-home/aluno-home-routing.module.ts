import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunoHomeComponent } from './aluno-home.component';

const routes: Routes = [
  {
    path: '',
    children:[
      {
        path:'',
        component: AlunoHomeComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunoHomeRoutingModule { }
