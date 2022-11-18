import { NutriHomeComponent } from './nutri-home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { AvalicaoFisicaComponent } from './avalicao-fisica/avalicao-fisica.component';

const routes: Routes = [
  {
    path: '',
    component: NutriHomeComponent,
    children:[
      {
        path: '',
        component : SearchComponent
      },
      {
        path: 'avaliacaoFisica/:idAluno',
        component : AvalicaoFisicaComponent
      },
    ],
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NutriHomeRoutingModule { }
