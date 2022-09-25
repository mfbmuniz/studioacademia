import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Alunos } from 'src/app/Models/aluno';
import { Router } from '@angular/router';
import { Exercicio,Exercicios } from 'src/app/Models/exercicio';
import {UserService} from "../../../services/UserService";
import {ExerciseService} from "../../../services/ExerciseService";

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router : Router,
    private userService : UserService,
    private exerciseService : ExerciseService,
    ) { }

  searchForm !: FormGroup
  content$ !: {}
  hasValuesToLoad: boolean = false;
  isSearchUser: boolean = true;

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      typeSearch : ['',[Validators.required]],
      keySearch : ['',[Validators.required]],
    })


  }

  public search(){

    this.isSearchUser = ['user', ''].includes(this.searchForm?.value?.typeSearch)


    console.log(this.searchForm?.value?.typeSearch, this.isSearchUser)

    this.hasValuesToLoad = false
    // Faz a presquisa q tem q fazer e recarrega a página
    let keysearch = this.searchForm.value.keySearch;
    if(this.isSearchUser) {
      console.log('search user')
      this.userService.listByPage(0, 10, keysearch)
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    }else{
      console.log('search exercise')
      this.exerciseService.listByPage(0, 10, keysearch)
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    }
  }

}
