import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Department} from "../_models/department";


const DEPT_API_URL = '/rest/department';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class DepartmentService {

  constructor(private _http: HttpClient) { }

  add(dept: Department): Observable<Department> {
    return this._http.post<Department>(DEPT_API_URL + "/add", dept, HTTP_OPTIONS);
  }

  list(): Observable<Department[]> {
    return this._http.get<Department[]>(DEPT_API_URL + "/list");
  }
}
