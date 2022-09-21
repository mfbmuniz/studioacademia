import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Ficha, Fichas } from 'src/app/Models/ficha';


@Component({
  selector: 'app-grade-fichas',
  templateUrl: './grade-fichas.component.html',
  styleUrls: ['./grade-fichas.component.css']
})
export class GradeFichasComponent implements OnInit {

 fichas$ !: Observable<Fichas>
 teste = 1

  constructor() { }

  ngOnInit(): void {
    // this.ficha$ = .... Recuperação no banco
  }

}
