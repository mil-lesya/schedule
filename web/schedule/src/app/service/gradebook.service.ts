import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';
import {Assessment} from '../dto/Assessment';

@Injectable({
  providedIn: 'root'
})
export class GradebookService {

  student: Student;

  constructor(
    private http: HttpClient
  ) {
  }

  getGradebookAssessments(token: string): Observable<Assessment[]> {
    return this.http.get<Assessment[]>(API_URL + 'gradebook', {
      headers: {token: token.toString()}
    });
  }

  getStudentAssessments(studentId: number, token: string): Observable<Assessment[]> {
    return this.http.get<Assessment[]>(API_URL + 'gradebook/student', {
      params: {studentId: studentId.toString()},
      headers: {token: token.toString()}
    });
  }


  isHeadman(token: string) {
    return this.http.get<boolean>(API_URL + 'gradebook/headman', {
      headers: {token: token.toString()}
    });
  }

  getStudent(student: Student) {
    this.student = student;
    console.log(student);
  }
}
