import { Component, OnInit } from '@angular/core';
import {User} from "../../../../_models/user";
import {AuthService} from "../../../../_services/auth.service";
import {Company} from "../../../../_models/company";
import {UserService} from "../../../../_services/user.service";
import {CompanyService} from "../../../../_services/company.service";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  currUser: User;
  user: User = new User();
  company: Company = new Company();

  constructor(private _authService: AuthService,
              private _userService: UserService,
              private _compService: CompanyService) { }

  ngOnInit() {
    this.currUser = this._authService.getCurrentUser();
  }

  submit(form) {
    this.user.password = "1234";
    this._userService.add(this.user)
      .subscribe(user => {
        if (this.user.role == 'ORG_ADMIN') {
          this.company.admin = user;

          this._compService.add(this.company)
            .subscribe(company => console.log(company));
        }
      });
  }
}
