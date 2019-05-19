import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Attendance} from '../dto/Attendance';
import {NewAttendance} from '../dto/NewAttendance';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  constructor(
    private http: HttpClient
  ) {
  }

  getAttendance(token: string): Observable<Attendance[]> {
  return this.http.get<Attendance[]>(API_URL + 'attendance/get', {
  headers: {token: token}
});
}

  getStudentAttendance(studentId: number): Observable<Attendance[]> {
    return this.http.get<Attendance[]>(API_URL + 'attendance/student', {
      params: {studentId: studentId.toString()}
    });
  }

  deleteAttendance(attendanceId: number): Observable<void> {
    return this.http.post<void>(API_URL + 'attendance/delete', attendanceId);
  }

  addAttendance(newAttendance: NewAttendance): Observable<void> {
    return this.http.post<void>(API_URL + 'attendance/add', newAttendance);
  }

}
