import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exercicios } from 'src/app/Models/exercicio';
import { Ficha } from 'src/app/Models/ficha';
import { pageableObject } from 'src/app/Models/PageableObject';
import { AuthService } from 'src/app/services/AuthService';
import { UserFileService } from 'src/app/services/UserFileService';

@Component({
  selector: 'app-grade-exercicios',
  templateUrl: './grade-exercicios.component.html',
  styleUrls: ['./grade-exercicios.component.css']
})
export class GradeExerciciosComponent implements OnInit {

  ficha$ !: Ficha
  pageable !: pageableObject
  idFile: any;
  exercicios !: Exercicios;
  actualUser !: any
  fichaAuxiliar !: Ficha

  constructor(
    private userFileServise : UserFileService,
    private authService : AuthService,
    private routeAc : ActivatedRoute,
    )
    {
      this.routeAc.params.subscribe(params => this.idFile = params['idFile']);
      this.actualUser = this.authService.getSession().user.id
    }

  ngOnInit(): void {
    this.listExerciseUser(0,10,this.actualUser,this.idFile)
  }

  listExerciseUser(page : number, size : number, idUser : number , fileId : number){

    this.userFileServise.pegarFichaUser(fileId).subscribe({
      next: (res)=>{
        this.pageable = res,
        this.fichaAuxiliar = this.pageable?.content as unknown as Ficha
        this.exercicios = this.fichaAuxiliar.exercises
      },
      error: (err)=>{

      }
    })
  }

}
