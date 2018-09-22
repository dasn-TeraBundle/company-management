import { Component, OnInit } from '@angular/core';
import {Company} from "../../../../_models/company";
import {CompanyService} from "../../../../_services/company.service";
import {AuthService} from "../../../../_services/auth.service";
import {User} from "../../../../_models/user";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  user: User;
  companies: Company[];

  constructor(private _compService: CompanyService,
              private _authService: AuthService) { }

  ngOnInit() {
    this.user = this._authService.getCurrentUser();
    this.listCompanies();
  }

  private listCompanies() {
    this._compService.list()
      .subscribe(companies => this.companies = companies);
  }
}
