import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {

  fichas$ !: Observable<Fichas>

  constructor() { }

  ngOnInit(): void {
    // this.fichas$ = -> chama as fichas do aluno no bd
  }

  public delete() : void{
    alert()
  }

}
