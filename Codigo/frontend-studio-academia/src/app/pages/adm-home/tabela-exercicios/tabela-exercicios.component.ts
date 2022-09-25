import {Component, Input, OnInit} from '@angular/core';
import {Exercicio, Exercicios} from 'src/app/Models/exercicio';
import {pageableObject} from "../../../Models/PageableObject";

@Component({
  selector: 'app-tabela-exercicios',
  templateUrl: './tabela-exercicios.component.html',
  styleUrls: ['./tabela-exercicios.component.css']
})
export class TabelaExerciciosComponent implements OnInit {

  @Input() exercicios !: Exercicios
  @Input() pageable !: pageableObject

  constructor() {}

  ngOnInit(): void {
    this.exercicios = <Exercicios>this.pageable?.content
  }

}
