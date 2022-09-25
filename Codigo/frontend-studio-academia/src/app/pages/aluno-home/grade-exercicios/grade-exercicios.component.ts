import { Component, OnInit } from '@angular/core';
import { Ficha } from 'src/app/Models/ficha';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-grade-exercicios',
  templateUrl: './grade-exercicios.component.html',
  styleUrls: ['./grade-exercicios.component.css']
})
export class GradeExerciciosComponent implements OnInit {

  ficha$ !: Ficha
  pageable !: pageableObject

  constructor() { }

  ngOnInit(): void {

    this.ficha$ = <Ficha><unknown>this.pageable?.content
  }

}
