import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthStudent} from '../dto/AuthStudent';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';

@Injectable({
  providedIn: 'root'
})

export class AuthStudentService {

  constructor(private http: HttpClient) {
  }

  authenticate(authStudent: AuthStudent): Observable<string> {
    return this.http.post<string>(API_URL + 'auth/student', authStudent, {responseType: 'text' as 'json'});
  }

  validate(token: string): Observable<Student> {
    return this.http.get<Student>(API_URL + 'auth/student/validate', {
      headers: {token: token}
    });
  }


}
