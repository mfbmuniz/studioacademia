import { Component, Input ,OnInit } from '@angular/core';
import { pageableObject } from 'src/app/Models/PageableObject';
import { Users } from 'src/app/Models/user';

@Component({
  selector: 'app-grade-alunos',
  templateUrl: './grade-alunos.component.html',
  styleUrls: ['./grade-alunos.component.css']
})
export class GradeAlunosComponent implements OnInit {

  @Input() pageable !: pageableObject
  users !: Users

  constructor() { }

  ngOnInit(): void {
    this.users = <Users>this.pageable?.content
  }

}
