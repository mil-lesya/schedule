import {Injectable} from '@angular/core';
import {Student} from '../dto/Student';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {Schedule} from '../dto/Schedule';
import {ExpectedGroup} from '../dto/ExpectedGroup';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  student: Student;

  constructor(
    private http: HttpClient
  ) {
  }

  getSchedule(token: string): Observable<Schedule[]> {
    return this.http.get<Schedule[]>(API_URL + 'schedule/get', {
      headers: {token: token}
    });
  }
  getGroupSchedule(expectedGroup: ExpectedGroup): Observable<Schedule[]> {
    return this.http.post<Schedule[]>(API_URL + 'schedule/group', expectedGroup, {responseType: 'text' as 'json'});
  }

  saveSchedule(schedule: Schedule[]): Observable<void> {
    return this.http.post<void>(API_URL + 'schedule/save', schedule);
  }
}
