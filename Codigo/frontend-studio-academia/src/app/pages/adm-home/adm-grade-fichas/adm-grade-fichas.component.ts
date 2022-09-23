import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {
  emailAluno : any
  fichas$ !: Observable<Fichas>

  constructor(private routeAR: ActivatedRoute) {
    this.routeAR.params.subscribe(params => this.emailAluno = params['id']);
   }

  ngOnInit(): void {
    // this.fichas$ = -> chama as fichas do aluno no bd
  }

  public delete(nome : String) : void{
    alert()
  }

}
