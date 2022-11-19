import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdmHomeComponent} from "./pages/adm-home/adm-home.component";
import {AuthGuard} from "./shared/guards/AuthGuard";


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    loadChildren : ()=> import('./pages/homepage/homepage.module').then((m)=>m.HomepageModule)
  },
  {
    path: 'adm',
    //canActivate: [AuthGuard],
    loadChildren: ()=> import('./pages/adm-home/adm-home.module').then((m)=>m.AdmHomeModule)
  },
  {
    path: 'aluno',
    canActivate: [AuthGuard],
    loadChildren: ()=> import('./pages/aluno-home/aluno-home.module').then((m)=>m.AlunoHomeModule)
  },
  {
    path: 'nutri',
    loadChildren: ()=> import('./pages/nutri-home/nutri-home.module').then((m)=>m.NutriHomeModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
