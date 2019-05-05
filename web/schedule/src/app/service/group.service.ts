import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Student} from '../dto/Student';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(
    private http: HttpClient
  ) {
  }
  getGroup(): Observable<Student[]> {
    return this.http.get<Student[]>(API_URL + 'group', {

    });
  }
}
