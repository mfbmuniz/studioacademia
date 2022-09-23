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
  emailAluno !: String
  fichas$ !: Observable<Fichas>

  constructor(private routeAc: ActivatedRoute) {
    this.routeAc.params.subscribe(params => this.emailAluno = params['idAluno']);
   }

  ngOnInit(): void {
    // this.fichas$ = -> chama as fichas do aluno no bd
  }

  public delete(nome : String) : void{
    alert()
  }

}
