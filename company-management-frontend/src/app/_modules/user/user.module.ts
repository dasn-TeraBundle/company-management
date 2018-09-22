import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent, AddComponent, ListComponent, EditComponent } from './_components';
import {UserRoutingModule} from "./user-routing.module";
import {BmmmaterialModule} from "../bmmmaterial/bmmmaterial.module";
import {FormsModule} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {CompanyService} from "../../_services/company.service";

@NgModule({
  imports: [
    CommonModule,
    BmmmaterialModule,
    FormsModule,
    UserRoutingModule
  ],
  declarations: [AddComponent, HomeComponent, ListComponent, EditComponent],
  providers: [UserService, CompanyService]
})
export class UserModule { }
