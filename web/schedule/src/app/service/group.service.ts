import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';
import {ExpectedGroup} from '../dto/ExpectedGroup';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(
    private http: HttpClient
  ) {
  }

  getGroup(token: string): Observable<Student[]> {
    return this.http.get<Student[]>(API_URL + 'group/get', {
      headers: {token: token}
    });
  }

  getExpectedGroup(expectedGroup: ExpectedGroup): Observable<Student[]> {
    return this.http.post<Student[]>(API_URL + 'group', expectedGroup, {responseType: 'text' as 'json'});
  }
}
