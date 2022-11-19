import { PhysicalAssessments } from './../../../Models/physical-assessment';
import { Component, OnInit,Input } from '@angular/core';
import { pageableObject } from 'src/app/Models/PageableObject';

@Component({
  selector: 'app-grade-avaliacao-fisica',
  templateUrl: './grade-avaliacao-fisica.component.html',
  styleUrls: ['./grade-avaliacao-fisica.component.css']
})
export class GradeAvaliacaoFisicaComponent implements OnInit {

  @Input() pageable !: pageableObject
  dados !: PhysicalAssessments

  constructor() { }

  ngOnInit(): void {
    this.dados = this.pageable?.content as PhysicalAssessments
  }

}
