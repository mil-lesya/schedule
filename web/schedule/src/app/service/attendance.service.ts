import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Attendance} from '../dto/Attendance';

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


}
