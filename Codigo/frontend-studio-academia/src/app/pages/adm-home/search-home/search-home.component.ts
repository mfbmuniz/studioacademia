import { PhysicalAssessmentService } from './../../../services/physical-assessmentService.';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Alunos } from 'src/app/Models/aluno';
import { Router } from '@angular/router';
import { Exercicio, Exercicios } from 'src/app/Models/exercicio';
import { UserService } from "../../../services/UserService";
import { ExerciseService } from "../../../services/ExerciseService";

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private exerciseService: ExerciseService,
    private physicalAssessmentService:PhysicalAssessmentService,
  ) { }

  searchForm !: FormGroup
  content$ !: {}
  hasValuesToLoad: boolean = false;
  isSearchUser: boolean = true;
  isSearchExercise: boolean = true;
  isSearchPhysicalAssement: boolean = true;

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      typeSearch: ['', [Validators.required]],
      keySearch: ['', [Validators.required]],
    })


  }

  public search() {

    this.isSearchUser = ['user', ''].includes(this.searchForm?.value?.typeSearch)
    this.isSearchExercise = ['exercise', ''].includes(this.searchForm?.value?.typeSearch)
    this.isSearchPhysicalAssement = ['physical', ''].includes(this.searchForm?.value?.typeSearch)

    this.hasValuesToLoad = false

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
    }else if(this.isSearchExercise){

      this.exerciseService.listByPage(0, 10, keysearch)
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    }else if(this.isSearchPhysicalAssement ){
      this.physicalAssessmentService.listPhysicalAssessmentByName(0, 10, keysearch)
      .subscribe(
        (res: any) => {
          this.content$ = res;
          this.hasValuesToLoad = true
        },
      );
    }

  }

}
