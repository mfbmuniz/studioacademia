
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import { HomepageComponent } from './homepage.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { CarrosselComponent } from './carrossel/carrossel.component';



@NgModule({
  declarations: [HomepageComponent,LoginComponent, CarrosselComponent],
  imports: [
    CommonModule,
    HomepageRoutingModule,
    FormsModule,
  ],
  exports : [HomepageComponent]
})
export class HomepageModule { }
