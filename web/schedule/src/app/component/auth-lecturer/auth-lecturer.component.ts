import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';
import {AuthLecturerService} from '../../service/auth.lecturer.service';
import {ErrorService} from '../../service/error.service';
import {Login} from "../../dto/Login";

@Component({
  selector: 'app-auth-lecturer',
  templateUrl: './auth-lecturer.component.html',
  styleUrls: ['./auth-lecturer.component.scss']
})
export class AuthLecturerComponent implements OnInit {

  authLecturer: Login = new Login();

  constructor(
    private authLecturerService: AuthLecturerService,
    private router: Router,
    private tokenProviderService: TokenProviderService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authLecturerService.authenticate(this.authLecturer).subscribe(resp => {
        const token = resp.headers.get('Authorization');
        this.tokenProviderService.setToken(token);
        console.log('set token', token);
        localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

        this.router.navigate(['/feed/lecturer'], {replaceUrl: true});
    },
      err => this.errorService.raise(err));
  }

}
