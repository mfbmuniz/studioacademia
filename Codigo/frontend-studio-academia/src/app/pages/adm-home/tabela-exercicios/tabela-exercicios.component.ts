import {Component, Input, OnInit} from '@angular/core';
import {Exercicio, Exercicios} from 'src/app/Models/exercicio';
import { ExerciseService } from 'src/app/services/ExerciseService';
import {pageableObject} from "../../../Models/PageableObject";
import {Router} from "@angular/router";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-tabela-exercicios',
  templateUrl: './tabela-exercicios.component.html',
  styleUrls: ['./tabela-exercicios.component.css']
})
export class TabelaExerciciosComponent implements OnInit {

  @Input() exercicios !: Exercicios
  @Input() pageable !: pageableObject

  constructor(
    private exerciseService : ExerciseService,
    public router: Router,
    private toastr: ToastrService,) {}

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
              alert("Exercício apagado com êxito")
              this.router.navigateByUrl('adm/home')
            },
            error: (err) => {
              console.log(err)
              alert("Não foi possível deletar o exercício")
            }
          }
        );
      console.log(name)
    }
  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

}
