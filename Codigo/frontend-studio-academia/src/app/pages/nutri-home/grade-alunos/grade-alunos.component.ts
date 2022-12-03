import { PhysicalAssessmentService } from './../../../services/physical-assessmentService.';
import { Component, Input ,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { pageableObject } from 'src/app/Models/PageableObject';
import { Users } from 'src/app/Models/user';
import { AuthService } from 'src/app/services/AuthService';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-grade-alunos',
  templateUrl: './grade-alunos.component.html',
  styleUrls: ['./grade-alunos.component.css']
})
export class GradeAlunosComponent implements OnInit {


  @Input() pageable !: pageableObject
  users !: Users

  fileForm !: FormGroup
  alunoId !: String
  actualUser: any;

  erro : boolean = true
  path : any = null;

  constructor(
    private formBuilder : FormBuilder,
    private authService : AuthService,
    private physicalService : PhysicalAssessmentService,
    private toastr: ToastrService,) { }

  ngOnInit(): void {
    this.actualUser = this.authService.getSession().user.id
    this.users = <Users>this.pageable?.content
    this.fileForm = this.formBuilder.group({
      PhysicalAssessment : [[],Validators.required]
    })
  }

  send() {
    if( (!this.erro) && (this.path!=null) )
      this.showSuccessToastr()
    else
      this.showErrorToastr()
  }

  onFileSelected(event: any) {
    try {

      const pdfPhysicalAssessment : FormData = new FormData();
      pdfPhysicalAssessment.append('pdfPhysicalAssessment',event.target.files.item(0));


      this.physicalService.uploadFile(pdfPhysicalAssessment).subscribe({
        next:(res) => {

          this.erro=false;
          this.path = res
        },
        error: (err) => {
          console.log(err)

          this.erro=true;
        }
      })
    }catch (e) {
        console.log("erro, catch")
        console.log(e)
    }


    }

  public takeUserId(alunoId ?: String){
    this.alunoId = alunoId as String
  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }
}
