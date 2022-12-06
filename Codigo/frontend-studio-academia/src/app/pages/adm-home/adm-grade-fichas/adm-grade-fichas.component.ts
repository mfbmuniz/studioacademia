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
import { ToastrService } from 'ngx-toastr';

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
    private formBuilder : FormBuilder,
    private toastr: ToastrService,){

    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno'])


  }

  searchForm !: FormGroup
  content$ !: {}
  hasValuesToLoad: boolean = false;
  isSearchUserFile: boolean = true;
  gambs: boolean = true;
  gambiarra: boolean = true;

  ngOnInit(): void {

    console.log("fichas aluno")
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

      let body = {
      idUser: this.idAluno,
      fileName: this.novaFichaForm.value["fileName"],
    }

    this.userFileService.create(body).subscribe(
      {
        next: (res) => {
          this.router.navigate(['gradeFichas',this.idAluno])
          this.showSuccessToastr()
          console.log(res)
        },
        error: (err) => {
          console.log(err)
          this.showErrorToastr()
        },
        complete: () =>{
          this.searchFiles(this.idAluno)
        }
      }
    )
  }

  public searchFiles(idUser : String){




    // console.log(this.searchForm, this.isSearchUserFile)
    this.hasValuesToLoad = false


    if(this.isSearchUserFile) {
      // console.log('search userFile', idUser)
      this.userFileService.listUserFilesByPageWithSize(0, 10,idUser,''  )
        .subscribe(
          (res: any) => {
            this.fichas = <Fichas>res?.content
            this.hasValuesToLoad = true
          },
        );
    }else{
      // console.log('search exercise')
      this.exerciseService.listByPage(0, 10, '')
        .subscribe(
          (res: any) => {
            this.content$ = res;
            this.hasValuesToLoad = true
          },
        );
    }
  }

  delete(userFileId: string | undefined) {
    let nomeFicha = this.fichas.filter(f => f.userFileId == userFileId)[0]?.fileName

    if(confirm("Tem certeza que deseja deletar?") && nomeFicha){
      this.userFileService.delete(this.fichas.filter(f => f.userFileId == userFileId)[0]?.userFileId)
        .subscribe(
          {
            next:(res) => {
              console.log(res)
             this.showSuccessToastr()
              this.router.navigateByUrl('adm/home')
            },
            error: (err) => {
              console.log(err)
              alert("Não foi possível deletar a ficha")
            },
            complete: () => {
              this.searchFiles(this.idAluno)
            }
          }
        );
      // console.log(nomeFicha)
    }
  }

  showSuccessToastr(){
    this.toastr.success("Apagado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("Não foi possível apagar", "Erro")
  }

}
