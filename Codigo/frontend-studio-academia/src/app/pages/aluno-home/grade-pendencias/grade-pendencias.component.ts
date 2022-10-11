import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-grade-pendencias',
  templateUrl: './grade-pendencias.component.html',
  styleUrls: ['./grade-pendencias.component.css']
})
export class GradePendenciasComponent implements OnInit {

  comprovanteForm !: FormGroup

  constructor(private formBuilder: FormBuilder,) { }

  ngOnInit(): void {
    this.comprovanteForm = this.formBuilder.group({
      test :['',[]],
    })
  }

  public onSubmit() : void{
    const teste = this.comprovanteForm.getRawValue()
    console.log(teste)
  }

}
