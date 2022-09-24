import { Component, OnInit, Input } from '@angular/core';
import { Exercicio } from 'src/app/Models/exercicio';

@Component({
  selector: 'app-tabela-exercicios',
  templateUrl: './tabela-exercicios.component.html',
  styleUrls: ['./tabela-exercicios.component.css']
})
export class TabelaExerciciosComponent implements OnInit {

  @Input() exercicios !: Array<Exercicio>
  constructor() { }

  ngOnInit(): void {
  }

}
