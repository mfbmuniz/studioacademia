import { Component, OnInit } from '@angular/core';
import { Messages } from 'src/app/Models/comments';
import { pageableObject } from 'src/app/Models/PageableObject';
import { MessageService } from 'src/app/services/messageService';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  messages !: Messages
  pageable !: pageableObject

  title !: string
  conteudo !: string

  constructor(private messageService : MessageService) { }

  ngOnInit(): void {
    this.listMessage(0,10)
  }

  listMessage(page : number, size : number){
    this.messageService.ListMessages(page,size).subscribe({
      next : (res) =>{
        this.pageable = res;
        this.messages = this.pageable?.content as Messages ;
      },
      error : (err) =>{
        console.log(err)
      }
    })
  }



  getContent(title :string | undefined,content :string | undefined) {
    this.conteudo = content as string
    this.title = title as  string
  }
}
