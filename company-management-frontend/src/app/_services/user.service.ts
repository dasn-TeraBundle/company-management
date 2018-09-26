import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../_models/user";


const USER_API_URL = '/rest/user';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  constructor(private _http: HttpClient) { }

  add(user: User): Observable<User> {
    return this._http.post<User>(USER_API_URL + "/add", user, HTTP_OPTIONS);
  }

  list(): Observable<User[]> {
    return this._http.get<User[]>(USER_API_URL + "/list");
  }

  delete(id: string): Observable<any> {
    return this._http.delete<any>(USER_API_URL + "/delete/" + id);
  }
}
