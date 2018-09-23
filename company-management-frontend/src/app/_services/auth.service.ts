import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../_models/user';
import {Observable} from 'rxjs/Observable';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';


const USER_API_URL = '/rest/user';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class AuthService {

  constructor(private _http: HttpClient) {
  }

  login(credentials: any): Observable<any> {
    let loginUrl = "/auth/login";
    let formData = new FormData();
    formData.append("username", credentials.username);
    formData.append("password", credentials.password);

    return this._http.post(loginUrl, formData)
      .pipe(
        map((res: any) => {
          sessionStorage.setItem("isLoggedIn", "1");
          return res;
        })
      );
  }

  getProfile(): Observable<User> {
    return this._http.get<User>(USER_API_URL + "/profile")
      .pipe(
        map((res: User) => {
          this.saveProfile(res);
          return res;
        })
      );
  }

  isLoggedIn(): boolean {
    let isLoggedIn = sessionStorage.getItem("isLoggedIn");
    if (isLoggedIn)
      return true;
    return false;
  }

  logout() {
    sessionStorage.removeItem("isLoggedIn");
    sessionStorage.removeItem("firstname");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("email");
  }

  getCurrentUser(): User {
    const user = new User();
    user.firstname = sessionStorage.getItem("firstname");
    user.role = sessionStorage.getItem("role");
    user.email = sessionStorage.getItem("email");


    return user;
  }

  list(): Observable<any> {
    return this._http.get("/rest/user/list");
  }

  private saveProfile(user: User) {
    sessionStorage.setItem("role", user.role);
    sessionStorage.setItem("firstname", user.firstname);
    sessionStorage.setItem("email", user.email);
  }
}
