import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Document} from "../_models/document";


const DOC_API_URL = '/rest/docs';
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class DocumentService {

  constructor(private _http: HttpClient) { }

  add(fileToUpload: File): Observable<Document> {
    const fd = new FormData();
    fd.append("file", fileToUpload);
    const HTTP_OPTIONS = {
      headers: new HttpHeaders({'A': 'A'})
    };

    return this._http.post<Document>(DOC_API_URL + "/add", fd, HTTP_OPTIONS);
  }

  list(): Observable<any> {
    return this._http.get<any>(DOC_API_URL + "/list");
  }

  delete(id: string): Observable<any> {
    return this._http.delete(DOC_API_URL + "/delete/" + id);
  }
}
