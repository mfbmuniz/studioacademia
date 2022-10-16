import { Component, Input, OnInit } from '@angular/core';
import { Alunos } from 'src/app/Models/aluno';
import {pageableObject} from "../../../Models/PageableObject";
import {Users} from "../../../Models/user";
import {UserService} from "../../../services/UserService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-grade-alunos',
  templateUrl: './grade-alunos.component.html',
  styleUrls: ['./grade-alunos.component.css']
})
export class GradeAlunosComponent implements OnInit {

  @Input() alunos !: Alunos
  @Input() pageable !: pageableObject
  @Input() users !: Users


  constructor(public userService: UserService,
              public router: Router) { }

  ngOnInit(): void {
    this.users = <Users>this.pageable?.content
    this.alunos = <Alunos>this.pageable?.content
  }

  delete(idUser: string | undefined) {
    let email = this.users.filter(f => f.idUser == idUser)[0]?.email

    if(confirm("Tem certeza que deseja deletar?") && email){
      this.userService.delete(email)
        .subscribe(
          {
            next:(res) => {
              console.log(res)
              alert("Usuário apagado com êxito")
              this.router.navigateByUrl('adm/home')
            },
            error: (err) => {
              console.log(err)
              alert("Não foi possível deletar o usuário")
            }
          }
        );
      console.log(email)
    }
  }
}
