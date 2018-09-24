import { Component, OnInit } from '@angular/core';
import {Company} from "../../../../_models/company";
import {CompanyService} from "../../../../_services/company.service";
import {AuthService} from "../../../../_services/auth.service";
import {User} from "../../../../_models/user";
import {ToasterService} from "angular2-toaster";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  user: User;
  companies: Company[];

  constructor(private _compService: CompanyService,
              private _authService: AuthService,
              private _toasterService: ToasterService) { }

  ngOnInit() {
    this.user = this._authService.getCurrentUser();
    this.listCompanies();
  }

  onDelete(comp: Company) {
    this._compService.delete(comp.id)
      .subscribe(res => {
        this._toasterService.pop("success", "Company Deleted")
        this.listCompanies();
      });
  }

  private listCompanies() {
    this._compService.list()
      .subscribe(companies => this.companies = companies);
  }
}
