import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { ActivatedRoute } from '@angular/router';
import { pageableObject } from 'src/app/Models/PageableObject';
import { UserFileService } from 'src/app/services/UserFileService';

@Component({
  selector: 'app-adm-grade-fichas',
  templateUrl: './adm-grade-fichas.component.html',
  styleUrls: ['./adm-grade-fichas.component.css']
})
export class AdmGradeFichasComponent implements OnInit {
  emailAluno !: String | 'teste'
  fichas$ !: Fichas
  pageable !: pageableObject


  constructor(private routeAc: ActivatedRoute, private userFileService : UserFileService) {
    this.routeAc.params.subscribe(params => this.emailAluno = params['idAluno'])
   }

  ngOnInit(): void {
    // this.fichas$ = <Fichas>this.pageables?.content
  }

  cadastrar(){
    let body = {
      id_user : this.emailAluno,
      name : "Nova Ficha",
    }

    this.userFileService.create(body).subscribe(
      {
        next:(res) => {
          console.log(res)
        },
        error: (err) => {
          console.log(err)
        }
      }
    )
  }

  public delete(id_file : String) : void{
    this.userFileService.delete(id_file).subscribe(
      {
        next:(res) => {
          console.log(res)
        },
        error: (err) => {
          console.log(err)
        }
      }
    )
  }

}
