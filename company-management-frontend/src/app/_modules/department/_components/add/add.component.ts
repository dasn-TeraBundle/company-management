import { Component, OnInit } from '@angular/core';
import {Department} from "../../../../_models/department";
import {AuthService} from "../../../../_services/auth.service";
import {DepartmentService} from "../../../../_services/department.service";
import {ToasterService} from "angular2-toaster";
import {CompanyService} from "../../../../_services/company.service";
import {Company} from "../../../../_models/company";
import {User} from "../../../../_models/user";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./../styles.css', './add.component.css']
})
export class AddComponent implements OnInit {

  currUser: User;
  dept: Department = new Department();

  companies: Company[] = [];

  constructor(private _authService: AuthService,
              private _compService: CompanyService,
              private _deptService: DepartmentService,
              private _toasterService: ToasterService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
    this._listCompanies();
  }

  onAdd(f) {
    this._deptService.add(this.dept)
      .subscribe(dept => {
        this._toasterService.pop('success', 'Department Added ID ' + dept.id);
        f.resetForm();
      });
  }

  private _listCompanies() {
    if (this.currUser.role == 'SYS_ADMIN')
      this._compService.list()
        .subscribe(comps => this.companies = comps);
  }
}
