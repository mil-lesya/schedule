import { Component, OnInit } from '@angular/core';
import {AuthStudent} from '../../dto/AuthStudent';
import {AuthStudentService} from '../../service/auth.student.service';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';
import {AuthLecturer} from '../../dto/AuthLecturer';
import {AuthLecturerService} from '../../service/auth.lecturer.service';

@Component({
  selector: 'app-auth-lecturer',
  templateUrl: './auth-lecturer.component.html',
  styleUrls: ['./auth-lecturer.component.scss']
})
export class AuthLecturerComponent implements OnInit {

  authLecturer: AuthLecturer = new AuthLecturer();

  constructor(
    private authLecturerService: AuthLecturerService,
    private router: Router,
    private tokenProviderService: TokenProviderService
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authLecturerService.authenticate(this.authLecturer).subscribe(token => {
      this.tokenProviderService.setToken(token);
      localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

      this.router.navigate(['/feed/lecturer'], {replaceUrl: true});
    });
  }

}
