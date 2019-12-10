import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../global';
import {Observable} from 'rxjs';
import {Lecturer} from '../dto/Lecturer';

@Injectable({
  providedIn: 'root'
})
export class LecturerService {

  constructor(
    private http: HttpClient
  ) {
  }

  getLecturer(token: string): Observable<Lecturer> {
    return this.http.get<Lecturer>(API_URL + 'lecturer', {
      headers: {Authorization: token.toString()}
    });
  }

}
