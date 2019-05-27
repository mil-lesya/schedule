import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor() {
  }

  raise(httpError: HttpErrorResponse) {
    console.log(typeof httpError.error);
    console.log(httpError.error);
    if (httpError.status === 400) {
      JSON.parse(httpError.error).errors.forEach(e => {
        alert(`ошибка: ${e.defaultMessage}`);
        console.log(`error: ${e.defaultMessage}`);
      });
    }
    if (httpError.status === 500) {
      alert(`ошибка: ${JSON.parse(httpError.error).message}`);
      console.log(JSON.parse(httpError.error).message);
    }
  }

}
