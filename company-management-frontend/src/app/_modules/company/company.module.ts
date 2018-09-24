import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent, ListComponent, AddComponent } from './_components';
import {CompanyRoutingModule} from "./company-routing.module";
import {CompanyService} from "../../_services/company.service";
import {BmmmaterialModule} from "../bmmmaterial/bmmmaterial.module";
import {FormsModule} from "@angular/forms";
import {ToasterModule} from "angular2-toaster";

@NgModule({
  imports: [
    CommonModule,
    BmmmaterialModule,
    FormsModule,
    ToasterModule.forRoot(),
    CompanyRoutingModule
  ],
  declarations: [HomeComponent, ListComponent, AddComponent],
  providers: [CompanyService]
})
export class CompanyModule { }
