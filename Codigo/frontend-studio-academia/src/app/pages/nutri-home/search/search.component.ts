import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { pageableObject } from 'src/app/Models/PageableObject';
import { UserService } from 'src/app/services/UserService';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchForm !: FormGroup
  hasValuesToLoad: boolean = false;
  content$ !: pageableObject

  constructor(private formBuilder: FormBuilder, private userService: UserService,) { }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      key: ['']
    })
  }

  search() {
    // console.log(this.searchForm.value)

    this.hasValuesToLoad = false
    var keysearch = this.searchForm.value.key;
    // console.log('pegou a keysearch')
    // console.log(keysearch)
    this.userService.listByPage(0, 10, keysearch)
      .subscribe(
        (res: any) => {
          this.content$ = res;
          this.hasValuesToLoad = true
        },
      );
  }

}
