import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddComponent, HomeComponent, ListComponent} from './_components';
import {AuthGuard} from "../../_guards/auth.guard";


const movieRoutes: Routes = [
  {
    path: '', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      {path: 'add', component: AddComponent},
      {path: '', component: ListComponent}
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(movieRoutes)
  ],
  exports: [RouterModule]
})
export class UserRoutingModule { }
