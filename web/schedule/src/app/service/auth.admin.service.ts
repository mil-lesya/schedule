import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Login} from "../dto/Login";

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService {

  constructor(private http: HttpClient) { }

  authenticate(admin: Login): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(API_URL + 'login', admin, {observe: 'response'});
  }

}
