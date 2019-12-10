import {Component, OnInit} from '@angular/core';
import {AuthStudentService} from '../../service/auth.student.service';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';
import {ErrorService} from '../../service/error.service';
import {Login} from "../../dto/Login";


@Component({
  selector: 'app-auth-student',
  templateUrl: './auth-student.component.html',
  styleUrls: ['./auth-student.component.scss']
})
export class AuthStudentComponent implements OnInit {

  error: any;
  authStudent: Login = new Login();

  constructor(
    private authStudentService: AuthStudentService,
    private router: Router,
    private tokenProviderService: TokenProviderService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authStudentService.authenticate(this.authStudent).subscribe(resp => {
        const token = resp.headers.get('Authorization');
        this.tokenProviderService.setToken(token);
        console.log('set token', token);
        localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

        this.router.navigate(['/feed/student'], {replaceUrl: true});
      },
      er => this.errorService.raise(er));
  }
}
