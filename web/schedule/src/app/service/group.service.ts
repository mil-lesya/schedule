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
      headers: {Authorization: token.toString()}
    });
  }

  getExpectedGroup(expectedGroup: ExpectedGroup, token: string): Observable<Student[]> {
    return this.http.post<Student[]>(API_URL + 'group/expected', expectedGroup, {
      responseType: 'text' as 'json',
      headers: {Authorization: token.toString()}
    });
  }

  getHeadmanId(expectedGroup: ExpectedGroup, token: string): Observable<number> {
    return this.http.post<number>(API_URL + 'group/headman', expectedGroup,       {
      responseType: 'text' as 'json',
      headers: {Authorization: token.toString()}
    });
  }

}
