import { Component, OnInit } from '@angular/core';
import {User} from "../../../../_models/user";
import {AuthService} from "../../../../_services/auth.service";
import {DepartmentService} from "../../../../_services/department.service";
import {Department} from "../../../../_models/department";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  currUser: User;
  depts: Department[];

  constructor(private _authService: AuthService,
              private _deptService: DepartmentService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
    this.listDepts();
  }

  private listDepts() {
    this._deptService.list()
      .subscribe(depts => this.depts = depts);
  }
}
