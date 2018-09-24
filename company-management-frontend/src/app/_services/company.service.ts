import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Company} from "../_models/company";


const COMP_API_URL = '/rest/company';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class CompanyService {

  constructor(private _http: HttpClient) { }

  list(): Observable<Company[]> {
    return this._http.get<Company[]>(COMP_API_URL + "/list");
  }

  add(company: Company): Observable<Company> {
    return this._http.post<Company>(COMP_API_URL + "/add", company, HTTP_OPTIONS);
  }

  update(dept: Company): Observable<Company> {
    return this._http.put<Company>(COMP_API_URL + "/update", dept, HTTP_OPTIONS);
  }

  delete(id: string): Observable<any> {
    return this._http.delete<any>(COMP_API_URL + "/delete/" + id);
  }
}
