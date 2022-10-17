import { Component, Input, OnInit } from '@angular/core';
import {pageableObject} from "../../../Models/PageableObject";
import {Users} from "../../../Models/user";
import {UserService} from "../../../services/UserService";
import {ActivatedRoute, Router} from "@angular/router";
import {Fichas} from "../../../Models/ficha";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../../../services/AuthService";
import {UserFileService} from "../../../services/UserFileService";
import {ExerciseService} from "../../../services/ExerciseService";
import { Observable } from 'rxjs';


@Component({
  selector: 'app-grade-listar-fichas',
  templateUrl: './grade-listar-fichas.html',
  styleUrls: ['./grade-listar-fichas.component.css']
})

export class GradeListarFichasComponent implements OnInit {

  novaFichaForm !: FormGroup
  idAluno !:  String
  auth!: AuthService

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

  ngOnInit(): void {

    this.fichas = <Fichas>this.pageable?.content
  }

  delete(userFileId: string | undefined) {
    let nomeFicha = this.fichas.filter(f => f.userFileId == userFileId)[0]?.fileName

    if(confirm("Tem certeza que deseja deletar?") && nomeFicha){
      this.userFileService.delete(this.fichas.filter(f => f.userFileId == userFileId)[0]?.userFileId)
        .subscribe(
          {
            next:(res) => {
              console.log(res)
              alert("Usuário apagado com êxito")
              this.router.navigateByUrl('adm/home')
            },
            error: (err) => {
              console.log(err)
              alert("Não foi possível deletar o usuário")
            }
          }
        );
      console.log(nomeFicha)
    }
  }
}
