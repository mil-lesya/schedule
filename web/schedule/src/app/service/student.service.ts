import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';
import {NewStudent} from '../dto/NewStudent';
import {Lecturer} from "../dto/Lecturer";

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
      headers: {Authorization: token.toString()}
    });
  }

  saveStudent(newStudent: NewStudent, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'student/new', newStudent, {
      responseType: 'text' as 'json',
      headers: {Authorization: token.toString()}
    } );
  }

  deleteStudent(studentId: number, token: string): Observable<void> {
    return this.http.get<void>(API_URL + 'student/delete', {
      headers: {Authorization: token.toString()},
      params: {studentId: studentId.toString()}
    });
  }

  appointHeadman(headmanId: number, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'student/headman', headmanId, {
      headers: {Authorization: token.toString()}
    });
  }

  searchLecturer(lecturer: string, token: string): Observable<Lecturer[]> {
    return this.http.get<Lecturer[]>(API_URL + 'student/lecturer', {
      headers: {Authorization: token.toString()},
      params: {lecturer: lecturer}
    });
  }

}
