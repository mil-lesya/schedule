import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Assessment} from '../dto/Assessment';
import {NewAssessment} from '../dto/NewAssessment';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(
    private http: HttpClient
  ) {
  }


  addAssessment(newAssessment: NewAssessment): Observable<void> {
    return this.http.post<void>(API_URL + 'gradebook/add', newAssessment);
  }

  deleteAssessment(assessmentId: number): Observable<void> {
    return this.http.post<void>(API_URL + 'assessment/delete', assessmentId);
  }
}
