import {Component, Input, OnInit} from '@angular/core';
import {Exercicio, Exercicios} from 'src/app/Models/exercicio';
import { ExerciseService } from 'src/app/services/ExerciseService';
import {pageableObject} from "../../../Models/PageableObject";
import {Router} from "@angular/router";

@Component({
  selector: 'app-tabela-exercicios',
  templateUrl: './tabela-exercicios.component.html',
  styleUrls: ['./tabela-exercicios.component.css']
})
export class TabelaExerciciosComponent implements OnInit {

  @Input() exercicios !: Exercicios
  @Input() pageable !: pageableObject

  constructor(private exerciseService : ExerciseService,  public router: Router) {}

  ngOnInit(): void {
    this.exercicios = <Exercicios>this.pageable?.content
  }


  delete(exerciseId: any) {
    let name = this.exercicios.filter(f => f.exerciseId == exerciseId)[0]?.name

    if(confirm("Tem certeza que deseja deletar?") && name){
      this.exerciseService.delete(exerciseId)
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
      console.log(name)
    }
  }

}
