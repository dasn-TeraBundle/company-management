import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent, AddComponent, ListComponent, EditComponent } from './_components';
import {UserRoutingModule} from "./user-routing.module";
import {BmmmaterialModule} from "../bmmmaterial/bmmmaterial.module";
import {FormsModule} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {CompanyService} from "../../_services/company.service";
import {DepartmentService} from "../../_services/department.service";
import {ToasterModule} from "angular2-toaster";

@NgModule({
  imports: [
    CommonModule,
    BmmmaterialModule,
    FormsModule,
    ToasterModule.forRoot(),
    UserRoutingModule
  ],
  declarations: [AddComponent, HomeComponent, ListComponent, EditComponent],
  providers: [UserService, CompanyService, DepartmentService]
})
export class UserModule { }
