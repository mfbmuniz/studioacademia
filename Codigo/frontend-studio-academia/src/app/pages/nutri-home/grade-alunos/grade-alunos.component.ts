import { Component, Input ,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { pageableObject } from 'src/app/Models/PageableObject';
import { Users } from 'src/app/Models/user';
import { AuthService } from 'src/app/services/AuthService';

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

  constructor(private formBuilder : FormBuilder,
    private authService : AuthService) { }

  ngOnInit(): void {
    this.actualUser = this.authService.getSession().user.id
    this.users = <Users>this.pageable?.content
    this.fileForm = this.formBuilder.group({
      PhysicalAssessment : [[],Validators.required]
    })
  }

  send() {
  //  console.log(this.fileForm.value)

  }

  public takeUserId(alunoId ?: String){
    this.alunoId = alunoId as String
  }
}
