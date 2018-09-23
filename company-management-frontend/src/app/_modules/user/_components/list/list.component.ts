import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../../_services/auth.service";
import {User} from "../../../../_models/user";
import {UserService} from "../../../../_services/user.service";
import {Company} from "../../../../_models/company";
import {CompanyService} from "../../../../_services/company.service";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  currUser: User;
  users: User[];
  companyMap: any = {};

  constructor(private _authService: AuthService,
              private _userService: UserService,
              private _compService: CompanyService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
    this.listCompanies();
    this.listUsers();
  }

  private listUsers() {
    this._userService.list()
      .subscribe(users => {
        this.users = users.filter(user => user.email != this.currUser.email);
      });
  }

  private listCompanies() {
    if (this.currUser.role == 'SYS_ADMIN')
      this._compService.list()
      .subscribe(companies => {
        this.companyMap = companies.reduce((acc, company) => {
          acc[company.admin.email] = company;
          return acc;
        }, {});

        console.log(this.companyMap);
      });
  }
}
