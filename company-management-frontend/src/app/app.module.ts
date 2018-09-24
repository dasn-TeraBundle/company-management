import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BmmmaterialModule} from "./_modules/bmmmaterial/bmmmaterial.module";
import {AuthService} from "./_services/auth.service";
import {AppRoutingModule} from "./app-routing.module";
import {AuthGuard} from "./_guards/auth.guard";
import {CompanyModule} from "./_modules/company/company.module";
import {DepartmentModule} from "./_modules/department/department.module";
import {UserModule} from "./_modules/user/user.module";
import {DocumentModule} from "./_modules/document/document.module";


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,

    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    BmmmaterialModule,

    AppRoutingModule,
    CompanyModule,
    DepartmentModule,
    UserModule,
    DocumentModule
  ],
  providers: [AuthGuard, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
