import {Injectable} from '@angular/core';
import {Admin} from '../dto/Admin';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService {

  constructor(private http: HttpClient) { }

  authenticate(admin: Admin): Observable<string> {
    return this.http.post<string>(API_URL + 'admin/auth', admin, {responseType: 'text' as 'json'});

  }

}
