import { Injectable } from '@angular/core';
import {Admin} from '../dto/Admin';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {HttpClient} from '@angular/common/http';
import {AuthStudent} from '../dto/AuthStudent';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService {

  constructor(private http: HttpClient) { }

  authenticate(admin: Admin): Observable<boolean> {
    return this.http.post<boolean>(API_URL + 'admin/auth', admin, {responseType: 'text' as 'json'});

  }
}
