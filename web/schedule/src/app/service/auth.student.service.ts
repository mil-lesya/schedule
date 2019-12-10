import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {Login} from "../dto/Login";

@Injectable({
  providedIn: 'root'
})

export class AuthStudentService {

  constructor(private http: HttpClient) {
  }

  authenticate(authStudent: Login): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(API_URL + 'login', authStudent, {observe: 'response'});
  }

}
