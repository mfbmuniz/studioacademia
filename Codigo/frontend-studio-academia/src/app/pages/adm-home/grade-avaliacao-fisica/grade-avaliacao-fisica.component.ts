import { PhysicalAssessments } from './../../../Models/physical-assessment';
import { Component, OnInit,Input } from '@angular/core';
import { pageableObject } from 'src/app/Models/PageableObject';
import {MonthlyPaymentService} from "../../../services/monthly-paymentService";
import {PhysicalAssessmentService} from "../../../services/physical-assessmentService.";
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-grade-avaliacao-fisica',
  templateUrl: './grade-avaliacao-fisica.component.html',
  styleUrls: ['./grade-avaliacao-fisica.component.css']
})
export class GradeAvaliacaoFisicaComponent implements OnInit {

  @Input() pageable !: pageableObject
  dados !: PhysicalAssessments

  pdf!: any
  pdfToShow: any;
  isPdfLoading: any;
  sanitizedUrl:any;

  constructor(
    private sanitizer:DomSanitizer,
    private physicalAssessmentService : PhysicalAssessmentService,

  ) {}

  ngOnInit(): void {
    this.dados = this.pageable?.content as PhysicalAssessments
  }


  createPdfFromBlob(pdf: Blob) {
    let reader = new FileReader();

    reader.addEventListener("application/pdf", () => {
      this.pdfToShow = reader.result;
    }, false);

    if (pdf) {
      reader.readAsDataURL(pdf);
      //window.open(fileURL, '_blank');
    }

  }
  getPdf(physicalAssessmentId: any) {
    this.isPdfLoading = true;

    this.physicalAssessmentService.getPdf(physicalAssessmentId).subscribe(data =>{
        this.createPdfFromBlob(data);
        this.isPdfLoading = false;
      },
      error => {
        this.isPdfLoading = false;
        this.pdfToShow = null;
        console.log(error);
      });
  }

  sanitize(filePath: any ) {
    return this.sanitizer.bypassSecurityTrustUrl(filePath);
  }
}
