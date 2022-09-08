
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import { HomepageComponent } from './homepage.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { CarrosselComponent } from './carrossel/carrossel.component';
import { MensagemModule } from 'src/app/components/mensagem/mensagem.module';



@NgModule({
  declarations: [HomepageComponent,LoginComponent, CarrosselComponent],
  imports: [
    CommonModule,
    HomepageRoutingModule,
    FormsModule,
    MensagemModule
  ],
  exports : [HomepageComponent]
})
export class HomepageModule { }
