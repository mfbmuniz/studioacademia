import { Observable } from 'rxjs';
import { Component,Input, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';
import { pageableObject } from 'src/app/Models/PageableObject';


@Component({
  selector: 'app-grade-fichas',
  templateUrl: './grade-fichas.component.html',
  styleUrls: ['./grade-fichas.component.css']
})
export class GradeFichasComponent implements OnInit {

 fichas$ !: Fichas
 pageable !: pageableObject

  constructor() { }

  ngOnInit(): void {
    // this.ficha$ = <Fichas>this.pageable?.content
  }

}
