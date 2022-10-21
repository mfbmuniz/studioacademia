import { Observable } from 'rxjs';
import {Component, Input, OnInit} from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { ActivatedRoute, Router } from '@angular/router';
import { pageableObject } from 'src/app/Models/PageableObject';
import { UserFileService } from 'src/app/services/UserFileService';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/AuthService";
import {UserService} from "../../../services/UserService";
import {ExerciseService} from "../../../services/ExerciseService";
import {Users} from "../../../Models/user";

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {

  novaFichaForm !: FormGroup
  idAluno !:  String
  auth!: AuthService
  savedId !:  String


  @Input() fichas !: Fichas
  @Input() pageable !: pageableObject
  @Input() users !: Users



  constructor(
    private routeAc: ActivatedRoute,
    private userFileService: UserFileService,
    private router : Router,
    private userService : UserService,
    private exerciseService : ExerciseService,
    private formBuilder : FormBuilder){

    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno'])


  }

  searchForm !: FormGroup
  content$ !: {}
  hasValuesToLoad: boolean = false;
  isSearchUserFile: boolean = true;
  gambs: boolean = true;
  gambiarra: boolean = true;

  ngOnInit(): void {


    this.novaFichaForm = this.formBuilder.group({
      idUser : [this.idAluno,],
      fileName: [""],
    })

    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno'])

    this.searchFiles(this.idAluno);

    this.fichas = <Fichas>this.pageable?.content
  }

  ngOnLoad(): void {
    if (this.gambiarra == true){
      this.searchFiles(this.idAluno);
      this.gambiarra=false;
    }
  }
  cadastrar() {


    console.log(this.novaFichaForm.value["fileName"])
    console.log("teste form")


      let body = {
      idUser: this.idAluno,
      fileName: this.novaFichaForm.value["fileName"],
    }

    this.userFileService.create(body).subscribe(
      {
        next: (res) => {
          this.router.navigate(['gradeFichas',this.idAluno])
          console.log(res)
        },
        error: (err) => {
          console.log(err)
        }
      }
    )
  }

  public searchFiles(idUser : String){

    //this.isSearchUserFile = ['userFile', ''].includes(this.searchForm?.value?.typeSearch)


    console.log(this.searchForm?.value?.typeSearch, this.isSearchUserFile)
    console.log('óiiii')
    this.hasValuesToLoad = false
    // Faz a presquisa q tem q fazer e recarrega a página
    //let keysearch = this.searchForm.value.keySearch;

    if(this.isSearchUserFile) {
      console.log('search userFile')
      this.userFileService.listUserFilesByPageWithSize(0, 10,idUser,''  )
        .subscribe(
          (res: any) => {
            this.content$ =  res;
            this.fichas = <Fichas>this.pageable?.content
            this.hasValuesToLoad = true
          },
        );
    }else{
      console.log('search exercise')
      this.exerciseService.listByPage(0, 10, '')
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    }
  }

  public delete(idFile: String): void {
    this.userFileService.delete(idFile).subscribe(
      {
        next: (res) => {
          console.log(res)
          this.router.navigate(['gradeFichas',this.idAluno])
        },
        error: (err) => {
          console.log(err)
        }
      }
    )
  }

}
