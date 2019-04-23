import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RegisterStudent} from "../dto/RegisterStudent";
import {Observable} from "rxjs";
import {API_URL} from "../../global";

@Injectable({
  providedIn: 'root'
})
export class RegisterStudentService {

  constructor(private http: HttpClient) {
  }

  register(registerStudent: RegisterStudent): Observable<void> {
    return this.http.post<void>(API_URL + 'register/student', registerStudent);
  }

}
