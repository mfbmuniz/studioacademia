import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MensagemComponent } from './mensagem.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    MensagemComponent
  ],
  imports: [
    CommonModule,
  ],
  exports:[
    MensagemComponent
  ]
})
export class MensagemModule { }
