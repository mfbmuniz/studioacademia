import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pag-alert',
  templateUrl: './pag-alert.component.html',
  styleUrls: ['./pag-alert.component.css']
})
export class PagAlertComponent implements OnInit {

  @Input() mensagem = '';
  constructor() { }

  ngOnInit(): void {
  }

}
