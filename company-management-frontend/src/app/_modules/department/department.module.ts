import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditComponent } from './_components/edit/edit.component';
import { HomeComponent, ListComponent,  AddComponent} from './_components';
import {DepartmentRoutingModule} from "./department-routing.module";

@NgModule({
  imports: [
    CommonModule,
    DepartmentRoutingModule
  ],
  declarations: [EditComponent, HomeComponent, ListComponent, AddComponent]
})
export class DepartmentModule { }
