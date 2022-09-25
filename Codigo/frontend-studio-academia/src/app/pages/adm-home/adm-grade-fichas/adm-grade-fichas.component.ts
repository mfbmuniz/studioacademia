import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { ActivatedRoute } from '@angular/router';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {
  emailAluno !: String | 'teste'
  fichas$ !: Fichas
  pageable !: pageableObject


  constructor(private routeAc: ActivatedRoute) {
    this.routeAc.params.subscribe(params => this.emailAluno = params['idAluno']);
   }

  ngOnInit(): void {
    // this.fichas$ = <Fichas>this.pageables?.content
  }

  public delete(nome : String) : void{
    alert()
  }

}
