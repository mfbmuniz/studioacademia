import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exercicio, Exercicios } from 'src/app/Models/exercicio';
import { Ficha } from 'src/app/Models/ficha';
import { pageableObject } from 'src/app/Models/PageableObject';
import { UserExercises } from 'src/app/Models/user-exercises';
import { AuthService } from 'src/app/services/AuthService';
import { UserFileService } from 'src/app/services/UserFileService';
import {User} from "../../../Models/user";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-grade-exercicios',
  templateUrl: './grade-exercicios.component.html',
  styleUrls: ['./grade-exercicios.component.css']
})
export class GradeExerciciosComponent implements OnInit {

  ficha$ !: Ficha
  pageable !: pageableObject
  idFile: any;
  exercicios !: Array<UserExercises>;
  actualUser !: any
  fichaAuxiliar !: UserExercises

  constructor(
    private userFileServise : UserFileService,
    private authService : AuthService,
    private routeAc : ActivatedRoute,
    private sanitizer: DomSanitizer
    )
    {}

  ngOnInit(): void {
    this.routeAc.params.subscribe(params => this.idFile = params['idFile']);
    this.actualUser = this.authService.getSession().user.id

    this.listExerciseUser(this.idFile)
  }

  listExerciseUser(fileId : number){
    // console.log("ALOU")
    this.userFileServise.pegarFichaUser(fileId).subscribe({
      next: (res)=>{
        this.pageable = res
        console.log(res)
        this.fichaAuxiliar = this.pageable?.content as unknown as UserExercises
        this.exercicios = res as Array<UserExercises>
          console.log("--->", res)
      },
      error: (err)=>{
        console.log(err)
      }
    })
  }

  getUrl(exerciseUrl: any) {

    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=)([^#&?]*).*/;
    const match = exerciseUrl.match(regExp);



    console.log('https://www.youtube.com/embed/'+match[2], match)
    return (match && match[2].length === 11)
        ? this.sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/'+match[2])
      : "";
  }
}
