import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { ActivatedRoute, Router } from '@angular/router';
import { pageableObject } from 'src/app/Models/PageableObject';
import { UserFileService } from 'src/app/services/UserFileService';
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../../services/AuthService";

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {

  fichas$ !: Fichas
  pageable !: pageableObject
  idAluno !:  String
  auth!: AuthService



  constructor(
    private routeAc: ActivatedRoute,
    private userFileService: UserFileService,
    private router : Router) {
    this.routeAc.params.subscribe(params => this.idAluno = params['idAluno'])

  }

  ngOnInit(): void {
    // this.fichas$ = <Fichas>this.pageables?.content
  }

  cadastrar() {
    let body = {
      idUser: this.idAluno,
      fileName: "Nova Ficha",
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
