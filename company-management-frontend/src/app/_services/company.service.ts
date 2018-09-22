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
}
