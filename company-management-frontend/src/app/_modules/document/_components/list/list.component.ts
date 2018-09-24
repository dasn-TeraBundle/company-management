import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../../_services/auth.service";
import {DocumentService} from "../../../../_services/document.service";
import {User} from "../../../../_models/user";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  currUser: User;

  all_global: Document[];
  all_comp_global: Document[];
  all_comp_dept_global: Document[];
  my_docs: Document[];

  constructor(private _authService: AuthService,
              private _docService: DocumentService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
  }

  private listDocuments() {

  }
}
