import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {User} from '../_models/user';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    user: User = new User();
    role: boolean;

    homeUrl: string = "/";


    constructor(private _authService: AuthService) {
    }

    ngOnInit() {
      this.user = this._authService.getCurrentUser();
      console.log(this.user);
    }

    logout() {
        this._authService.logout();
    }
}
