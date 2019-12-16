import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Subject} from "../dto/Subject";

@Injectable({
  providedIn: 'root'
})
export class SubjectService {


  constructor(
    private http: HttpClient
  ) {
  }

  searchSubject(subject: string, token: string): Observable<Subject[]>{
    return this.http.get<Subject[]>(API_URL + 'subject/search', {
      params: {subject: subject},
      headers: {Authorization: token.toString()}
    });
  }
}
