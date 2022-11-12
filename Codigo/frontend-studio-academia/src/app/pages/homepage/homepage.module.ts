
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageRoutingModule } from './homepage-routing.module';
import { HomepageComponent } from './homepage.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { CarrosselComponent } from './carrossel/carrossel.component';
import { MensagemModule } from 'src/app/components/mensagem/mensagem.module';
import {RouterModule} from "@angular/router";
import { PlanosComponent } from './planos/planos.component';



@NgModule({
  declarations: [HomepageComponent,LoginComponent, CarrosselComponent, PlanosComponent],
    imports: [
        CommonModule,
        HomepageRoutingModule,
        FormsModule,
        MensagemModule,
        ReactiveFormsModule,
    ],
  exports : [HomepageComponent]
})
export class HomepageModule { }
