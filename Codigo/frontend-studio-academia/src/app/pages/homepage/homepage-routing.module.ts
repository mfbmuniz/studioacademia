import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage.component';
import { CarrosselComponent } from './carrossel/carrossel.component';

const routes: Routes = [{
  path: '',
  component: HomepageComponent,
  children:[
    {
      path: '',
      component: CarrosselComponent
    },
    {
      path: 'login',
      component: LoginComponent
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomepageRoutingModule { }

