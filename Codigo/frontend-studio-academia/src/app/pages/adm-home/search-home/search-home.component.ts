import { Users } from './../../../Models/user';
import { pageableObject } from './../../../Models/PageableObject';
import { PhysicalAssessmentService } from './../../../services/physical-assessmentService.';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from "../../../services/UserService";
import { ExerciseService } from "../../../services/ExerciseService";
import { PageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit {

  aluno !: Users;
  pageable !: pageableObject

  searchForm !: FormGroup
  content$ !: {}
  hasValuesToLoad: boolean = false;
  isSearchUser: boolean = true;
  isSearchExercise: boolean = true;
  isSearchPhysicalAssement: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private exerciseService: ExerciseService,
    private physicalAssessmentService: PhysicalAssessmentService,
  ) { }



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
    if (this.isSearchUser) {
      // console.log('search user')
      this.userService.listByPage(0, 10, keysearch)
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    } else if (this.isSearchExercise) {

      this.exerciseService.listByPage(0, 10, keysearch)
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    } else if (this.isSearchPhysicalAssement) {

      if (keysearch == '') {
        this.physicalAssessmentService.listPhysicalAssessmentByUserId(0, 10, keysearch)
          .subscribe(
            (res: any) => {
              this.content$ = res;
              console.log(this.content$)
              this.hasValuesToLoad = true
            },
          );
      } else {
        this.userService.listByPage(0, 10, keysearch)
          .subscribe(
            (res: any) => {

              this.pageable = res as PageableObject
              this.aluno = this.pageable?.content as Users
              let alunoId = this.aluno[0].idUser as string
              this.physicalAssessmentService.listPhysicalAssessmentByUserId(0, 10, alunoId).subscribe(
                (res: any) => {
                  this.content$ = res;
                  console.log(this.content$)
                  this.hasValuesToLoad = true
                },
              );
            },
          );
      }

    }

  }

}
