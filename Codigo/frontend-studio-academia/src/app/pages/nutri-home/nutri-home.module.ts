import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NutriHomeRoutingModule } from './nutri-home-routing.module';
import { NutriHomeComponent } from './nutri-home.component';


@NgModule({
  declarations: [
    NutriHomeComponent
  ],
  imports: [
    CommonModule,
    NutriHomeRoutingModule
  ],
  exports : [
    NutriHomeComponent
  ]
})
export class NutriHomeModule { }
