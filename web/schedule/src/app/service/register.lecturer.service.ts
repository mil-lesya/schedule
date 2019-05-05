import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_URL} from '../../global';
import {RegisterLecturer} from '../dto/RegisterLecturer';

@Injectable({
  providedIn: 'root'
})
export class RegisterLecturerService {

  constructor(private http: HttpClient) {
  }

  register(registerLecturer: RegisterLecturer): Observable<void> {
    return this.http.post<void>(API_URL + 'register/lecturer', registerLecturer);
  }

}
