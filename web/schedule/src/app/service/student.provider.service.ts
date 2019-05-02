import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Student} from '../dto/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentProviderService {

  private meSubject = new BehaviorSubject<Student>(null);
  me = this.meSubject.asObservable();

  setMe(me: Student) {
    this.meSubject.next(me);
  }
}
