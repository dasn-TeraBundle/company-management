import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../../_services/auth.service";
import {User} from "../../../../_models/user";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  user: User;

  constructor(private _authService: AuthService) { }

  ngOnInit() {
    this.user = this._authService.getCurrentUser();
  }

}
