import { Component, OnInit } from '@angular/core';
import {AuthStudent} from '../../dto/AuthStudent';
import {AuthStudentService} from '../../service/auth.student.service';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';


@Component({
  selector: 'app-auth-student',
  templateUrl: './auth-student.component.html',
  styleUrls: ['./auth-student.component.scss']
})
export class AuthStudentComponent implements OnInit {


  authStudent: AuthStudent = new AuthStudent();

  constructor(
    private authStudentService: AuthStudentService,
    private router: Router,
    private tokenProviderService: TokenProviderService
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authStudentService.authenticate(this.authStudent).subscribe(token => {
      this.tokenProviderService.setToken(token);
      localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

      this.router.navigate(['/feed-student'], {replaceUrl: true});
    });
  }
}
