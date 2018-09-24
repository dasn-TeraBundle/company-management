import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent, AddComponent, ListComponent, EditComponent } from './_components';
import {BmmmaterialModule} from "../bmmmaterial/bmmmaterial.module";
import {FormsModule} from "@angular/forms";
import {ToasterModule} from "angular2-toaster";
import {DocumentRoutingModule} from "./document-routing.module";
import {DocumentService} from "../../_services/document.service";

@NgModule({
  imports: [
    CommonModule,
    BmmmaterialModule,
    FormsModule,
    ToasterModule.forRoot(),
    DocumentRoutingModule
  ],
  declarations: [HomeComponent, AddComponent, ListComponent, EditComponent],
  providers: [DocumentService]
})
export class DocumentModule { }
