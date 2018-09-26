import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../../_services/auth.service";
import {DocumentService} from "../../../../_services/document.service";
import {User} from "../../../../_models/user";
import {Document} from "../../../../_models/document";

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

    this.listDocuments();
  }

  onDelete(doc: Document) {
    this._docService.delete(doc.id)
      .subscribe(res => {
        this.listDocuments();
        alert("Delete Successful")
      });
  }

  private listDocuments() {
	  this._docService.list()
	  .subscribe(docs => {
		  if (this.currUser.role == 'SYS_ADMIN')
			  this.all_global = docs.ALL;
		  else if (this.currUser.role == "ORG_ADMIN") {
		    this.all_global = docs.ALL_GLOBAL;
		    this.all_comp_global = docs.ALL_COMPANY;
      } else if (this.currUser.role == "ORG_DEPT_ADMIN") {
        this.all_global = docs.ALL_GLOBAL;
        this.all_comp_global = docs.ALL_COMPANY_GLOBAL;
        this.all_comp_dept_global = docs.ALL_COMPANY_DEPT;
      } else if (this.currUser.role == "EMPLOYEE") {
        this.all_global = docs.ALL_GLOBAL;
        this.all_comp_global = docs.ALL_COMPANY_GLOBAL;
        this.all_comp_dept_global = docs.ALL_COMPANY_DEPT_GLOBAL;
        this.my_docs = docs.MY_DOCS;
      }
	  });
  }
}
