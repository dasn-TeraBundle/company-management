import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditComponent } from './_components/edit/edit.component';
import { HomeComponent, ListComponent,  AddComponent} from './_components';
import {DepartmentRoutingModule} from "./department-routing.module";
import {CompanyService} from "../../_services/company.service";
import {DepartmentService} from "../../_services/department.service";
import {UserService} from "../../_services/user.service";
import {FormsModule} from "@angular/forms";
import {ToasterModule} from "angular2-toaster";
import {BmmmaterialModule} from "../bmmmaterial/bmmmaterial.module";

@NgModule({
  imports: [
    CommonModule,
    BmmmaterialModule,
    FormsModule,
    ToasterModule.forRoot(),
    DepartmentRoutingModule
  ],
  declarations: [EditComponent, HomeComponent, ListComponent, AddComponent],
  providers: [UserService, CompanyService, DepartmentService]
})
export class DepartmentModule { }
