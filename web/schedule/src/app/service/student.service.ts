import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';
import {NewStudent} from '../dto/NewStudent';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private http: HttpClient
  ) {
  }

  getStudent(token: string): Observable<Student> {
    return this.http.get<Student>(API_URL + 'student/get', {
      headers: {token: token.toString()}
    });
  }

  saveStudent(newStudent: NewStudent, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'student/new', newStudent, {
      responseType: 'text' as 'json',
      headers: {token: token.toString()}
    } );
  }

  deleteStudent(studentId: number, token: string): Observable<void> {
    return this.http.get<void>(API_URL + 'student/delete', {
      headers: {token: token.toString()},
      params: {studentId: studentId.toString()}
    });
  }

  appointHeadman(headmanId: number): Observable<void> {
    return this.http.post<void>(API_URL + 'student/headman', headmanId);
  }

}
