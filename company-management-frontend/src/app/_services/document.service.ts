import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const DOC_API_URL = '/rest/docs';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class DocumentService {

  constructor(private _http: HttpClient) { }

  list(): Observable<any> {
    return this._http.get<any>(DOC_API_URL + "/list");
  }

  delete(id: string): Observable<any> {
    return this._http.delete(DOC_API_URL + "/delete/" + id);
  }
}
