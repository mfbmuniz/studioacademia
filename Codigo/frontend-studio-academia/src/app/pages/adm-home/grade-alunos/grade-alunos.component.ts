import { Component, Input, OnInit } from '@angular/core';
import { Alunos } from 'src/app/Models/aluno';
import {pageableObject} from "../../../Models/PageableObject";
import {Users} from "../../../Models/user";

@Component({
  selector: 'app-grade-alunos',
  templateUrl: './grade-alunos.component.html',
  styleUrls: ['./grade-alunos.component.css']
})
export class GradeAlunosComponent implements OnInit {

  @Input() alunos !: Alunos
  @Input() pageable !: pageableObject
  @Input() users !: Users


  constructor() { }

  ngOnInit(): void {
    this.users = <Users>this.pageable?.content
    this.alunos = <Alunos>this.pageable?.content
  }
}
