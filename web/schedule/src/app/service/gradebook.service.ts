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

  constructor(
    private http: HttpClient
  ) {
  }
  getGradebookAssessments(token: string): Observable<Assessment[]> {
    return this.http.get<Assessment[]>(API_URL + 'gradebook', {
      headers: {token: token}
    });
  }
}
