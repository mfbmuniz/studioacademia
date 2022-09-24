import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Alunos } from 'src/app/Models/aluno';
import { Router } from '@angular/router';
import { Exercicio,Exercicios } from 'src/app/Models/exercicio';

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router : Router
    ) { }

  searchForm !: FormGroup
  alunos$ !: Observable<Alunos>
  exercicios$ !: Observable<Exercicios>

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      keySearch : ['',[Validators.required]],
      typeSearch : ['',[Validators.required]],
    })
  }

  public search(){
    // Faz a presquisa q tem q fazer e recarrega a página


  }

}
