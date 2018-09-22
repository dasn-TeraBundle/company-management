import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../_services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  error: string;

  constructor(private _router: Router,
              private _authService: AuthService) { }

  ngOnInit() {
    localStorage.removeItem('bmm-user');
  }

  login() {
    this.model.username = this.model.email;
    this._authService.login(this.model)
      .subscribe(res => {
          this._router.navigate(['/']);
      }, err => this.error = 'Invalid credentials');
  }
}
