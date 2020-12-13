import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor() {
  }

  raise(httpError: HttpErrorResponse) {
    console.log(httpError.error);
    console.log(httpError.error.status);
    if (httpError.error.status === 400) {
      JSON.parse(httpError.error).errors.forEach(e => {
        alert(`ошибка: ${e.defaultMessage}`);
        console.log(`error: ${e.defaultMessage}`);
      });
    }
    if (httpError.error.status === 500) {
      alert(`ошибка: ${httpError.error.message}`);
    }
  }

}
