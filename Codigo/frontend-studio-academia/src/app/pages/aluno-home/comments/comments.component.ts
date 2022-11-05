import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/AuthService';
import { messageService } from 'src/app/services/messageService';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  commentsForm !: FormGroup
  actualUser !: any

  constructor(
    private formBuilder: FormBuilder,
    private authService : AuthService,
    private toastr: ToastrService,
    private messageService : messageService) { }

  ngOnInit(): void {
    this.commentsForm = this.formBuilder.group({
      title : ['',Validators.required],
      messageContent : ['',Validators.required],
      anonymous : []
    })

    this.actualUser = this.authService.getSession().user.id
  }

  send(){
    let body = {}

    if(this.commentsForm.value['anonymous']){
      body = {
        userId : null,
        messageContent : this.commentsForm.value['messageContent'],
        title : this.commentsForm.value['title'],
      }

    }else{
      body = {
        userId : this.actualUser,
        messageContent : this.commentsForm.value['messageContent'],
        title : this.commentsForm.value['title'],
      }

    }

    this.messageService.create(body).subscribe({
      next : (res)=>{
        this.showSuccessToastr()
        this.commentsForm.reset()
        console.log(res)
      },
      error : (err)=>{
        this.showErrorToastr()
        console.log(err)
      }
    })

  }

  showSuccessToastr(){
    this.toastr.success("Enviado com sucesso", "Sucesso")
  }

  showErrorToastr(){
    this.toastr.error("O envio não pode ser feito", "Erro")
  }

}
