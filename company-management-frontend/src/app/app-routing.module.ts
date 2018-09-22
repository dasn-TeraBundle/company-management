import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './_guards/auth.guard';


const appRoutes: Routes = [
  {
    path: '', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      {path: 'company', loadChildren: 'app/_modules/company/company.module#CompanyModule'},
      {path: 'department', loadChildren: 'app/_modules/department/department.module#DepartmentModule'},
      {path: 'user', loadChildren: 'app/_modules/user/user.module#UserModule'},
    ]
  },
  {path: 'login', component: LoginComponent},
  // {path: '', redirectTo: '/hall', pathMatch: 'full'}

];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {/*enableTracing: true,*/ useHash: true}
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
