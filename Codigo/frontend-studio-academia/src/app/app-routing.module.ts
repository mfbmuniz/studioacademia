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
    // component: HomepageComponent,
    loadChildren : ()=> import('./pages/homepage/homepage.module').then((m)=>m.HomepageModule)
  },
  {
    path: 'adm',
    // component: AdmHomeComponent,
    // canActivate: [AuthGuard],
    loadChildren: ()=> import('./pages/adm-home/adm-home.module').then((m)=>m.AdmHomeModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
