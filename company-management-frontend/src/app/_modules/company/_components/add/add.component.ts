import { Component, OnInit } from '@angular/core';
import {Company} from "../../../../_models/company";
import {User} from "../../../../_models/user";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  company: Company;

  constructor() { }

  ngOnInit() {
    this.company = new Company();
    this.company.admin = new User();
  }

}
