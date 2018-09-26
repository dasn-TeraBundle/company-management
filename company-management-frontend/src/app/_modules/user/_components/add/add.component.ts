import {Component, OnInit} from '@angular/core';
import {User} from "../../../../_models/user";
import {AuthService} from "../../../../_services/auth.service";
import {Company} from "../../../../_models/company";
import {UserService} from "../../../../_services/user.service";
import {CompanyService} from "../../../../_services/company.service";
import {DepartmentService} from "../../../../_services/department.service";
import {ToasterService} from "angular2-toaster";
import {Department} from "../../../../_models/department";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./../styles.css', './add.component.css']
})
export class AddComponent implements OnInit {

  currUser: User;
  user: User = new User();
  company: Company = new Company();
  department: Department = new Department();
  isAdding = false;

  compDeptMap: any = {};
  departments: Department[];
  compMap: any = {};
  compId: string;
  compIds: string[] = [];

  constructor(private _authService: AuthService,
              private _userService: UserService,
              private _compService: CompanyService,
              private _deptService: DepartmentService,
              private _toasterService: ToasterService) {
  }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
    this._listDepts();
  }

  submit(form) {
    this.isAdding = true;
    this.user.password = "1234";

    this._userService.add(this.user)
      .subscribe(user => {
        this._toasterService.pop('success', 'User Added Email : ' + user.email);
        if (this.user.role == 'ORG_ADMIN') {
          this.company.admin = user;
          this._toasterService.pop('info', 'Adding Company');

          this._compService.add(this.company)
            .subscribe(company => {
              this.isAdding = false;
              this._toasterService.pop('success', 'Company Added ID : ' + company.id);
            });
        } else if (this.user.role == 'ORG_DEPT_ADMIN') {
          this.department.admin = user;
          this._toasterService.pop('info', 'Adding Department');

          this._deptService.add(this.department)
            .subscribe(department => {
              this.isAdding = false;
              this._toasterService.pop("success", "Department Added ID : " + department.id);
            });
        } else
          this.isAdding = false;
      });
  }

  private _listDepts() {
    if (this.currUser.role == 'SYS_ADMIN' || this.currUser.role == 'ORG_ADMIN')
      this._deptService.list()
        .subscribe(depts => {
          this.departments = depts;
          this.compDeptMap = depts.reduce((acc, dept) => {
            acc[dept.company.id] = acc[dept.company.id] || [];
            acc[dept.company.id].push(dept);
            return acc;
          }, {});
          console.log(this.compDeptMap);
        });

    this._compService.list()
      .subscribe(companies => {
        this.compMap = companies.reduce((acc, company) => {
          acc[company.id] = company;
          this.compIds.push(company.id);
          return acc;
        }, {});

        console.log(this.compIds);
      });
  }
}
