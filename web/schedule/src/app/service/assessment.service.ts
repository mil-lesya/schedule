import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Assessment} from '../dto/Assessment';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(
    private http: HttpClient
  ) {
  }

  saveAssessments(assessments: Assessment[]): Observable<void> {
    return this.http.post<void>(API_URL + 'gradebook/edit', assessments);
  }
}
