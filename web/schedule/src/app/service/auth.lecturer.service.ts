import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {AuthLecturer} from '../dto/AuthLecturer';
import {Lecturer} from '../dto/Lecturer';

@Injectable({
  providedIn: 'root'
})

export class AuthLecturerService {

  constructor(private http: HttpClient) {
  }

  authenticate(authLecturer: AuthLecturer): Observable<string> {
    return this.http.post<string>(API_URL + 'auth/lecturer', authLecturer, {responseType: 'text' as 'json'});
  }

  validate(token: string): Observable<Lecturer> {
    return this.http.get<Lecturer>(API_URL + 'auth/validate', {
      headers: {token: token.toString()}
    });
  }

}
