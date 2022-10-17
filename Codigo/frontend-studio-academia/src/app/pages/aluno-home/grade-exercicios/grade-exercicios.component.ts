import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exercicios } from 'src/app/Models/exercicio';
import { Ficha } from 'src/app/Models/ficha';
import { pageableObject } from 'src/app/Models/PageableObject';
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

  constructor(private userFileServise : UserFileService,
    private routeAc : ActivatedRoute,) {
      this.routeAc.params.subscribe(params => this.idFile = params['idFile']);
    }

  ngOnInit(): void {
    this.listExerciseUser(0,10,1,this.idFile)
  }

  listExerciseUser(page : number, size : number, idUser : number , fileId : number){

    this.userFileServise.fileExerciseuser(page, size, idUser, fileId).subscribe({
      next: (res)=>{
        this.pageable = res,
        this.exercicios = <Exercicios>this.pageable?.content
      },
      error: (err)=>{

      }
    })
  }

}
