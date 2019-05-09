import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private http: HttpClient
  ) {
  }

  getStudent(token: string): Observable<Student> {
    return this.http.get<Student>(API_URL + 'feed/student/get', {
      headers: {token: token}
    });
  }
}
