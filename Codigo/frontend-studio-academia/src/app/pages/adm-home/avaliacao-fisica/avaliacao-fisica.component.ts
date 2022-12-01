import { PhysicalAssessment } from './../../../Models/physical-assessment';
import { PhysicalAssessmentService } from './../../../services/physical-assessmentService.';
import { Component, OnInit } from '@angular/core';
import { PageableObject } from 'src/app/Models/PageableObject';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-avaliacao-fisica',
  templateUrl: './avaliacao-fisica.component.html',
  styleUrls: ['./avaliacao-fisica.component.css']
})
export class AvaliacaoFisicaComponent implements OnInit {

  pageable$ !: PageableObject
  physical !: PhysicalAssessment
  physicalId !: string

  constructor(
    private physicalAssessmentService:PhysicalAssessmentService,
    private routeAc : ActivatedRoute,) {
      this.routeAc.params.subscribe(params => this.physicalId = params['idAvaliacao']);

     }

  ngOnInit(): void {
    console.log(this.physicalId)
    this.takePhysicalAssementById(this.physicalId)
  }

  takePhysicalAssementById(id : string){
    this.physicalAssessmentService.getPhysicalAssessmentById(id).subscribe({
      next: (res) =>{

        this.physical = res
        console.log(this.physical)
      },
      error(err) {
          console.log(err)
      },
    })
  }


}
