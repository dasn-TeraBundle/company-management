import { Component, OnInit } from '@angular/core';
import {User} from "../../../../_models/user";
import {AuthService} from "../../../../_services/auth.service";
import {DepartmentService} from "../../../../_services/department.service";
import {Department} from "../../../../_models/department";
import {ToasterService} from "angular2-toaster";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  currUser: User;
  depts: Department[];

  constructor(private _authService: AuthService,
              private _deptService: DepartmentService,
              private _toasterService: ToasterService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
    this.listDepts();
  }

  onDelete(dept: Department) {
    this._deptService.delete(dept.id)
      .subscribe(res => {
        this._toasterService.pop("success", "Department deleted");
        this.listDepts();
      });
  }

  private listDepts() {
    this._deptService.list()
      .subscribe(depts => this.depts = depts);
  }
}
