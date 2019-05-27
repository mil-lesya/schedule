import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {NewAssessment} from '../dto/NewAssessment';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(
    private http: HttpClient
  ) {
  }


  addAssessment(newAssessment: NewAssessment, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'assessment/add', newAssessment, {
      headers: {token: token.toString()}
    });
  }

  deleteAssessment(assessmentId: number, token: string): Observable<void> {
    return this.http.post<void>(API_URL + 'assessment/delete', assessmentId,
      {
        headers: {token: token.toString()}
      });
  }
}
