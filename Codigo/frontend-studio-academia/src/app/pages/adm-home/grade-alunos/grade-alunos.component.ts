import { Component, Input, OnInit } from '@angular/core';
import { Alunos } from 'src/app/Models/aluno';

@Component({
  selector: 'app-grade-alunos',
  templateUrl: './grade-alunos.component.html',
  styleUrls: ['./grade-alunos.component.css']
})
export class GradeAlunosComponent implements OnInit {

  @Input() alunos !: Alunos

  constructor() { }

  ngOnInit(): void {
  }

}
