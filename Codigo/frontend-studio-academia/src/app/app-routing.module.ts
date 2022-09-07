import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { LoginComponent } from './pages/homepage/login/login.component';


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
    loadChildren: ()=> import('./pages/adm-home/adm-home.module').then((m)=>m.AdmHomeModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
