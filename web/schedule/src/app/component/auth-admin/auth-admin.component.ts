import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthAdminService} from '../../service/auth.admin.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';
import {Login} from "../../dto/Login";

@Component({
  selector: 'app-auth-admin',
  templateUrl: './auth-admin.component.html',
  styleUrls: ['./auth-admin.component.scss']
})
export class AuthAdminComponent implements OnInit {

  admin: Login = new Login();
  isAdmin: boolean;

  constructor(
    private tokenProviderService: TokenProviderService,
    private errorService: ErrorService,
    private authAdminService: AuthAdminService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authAdminService.authenticate(this.admin).subscribe(resp => {
      const token = resp.headers.get('Authorization');
      this.tokenProviderService.setToken(token);
      console.log('set token', token);
      this.router.navigate(['/group/change'], {replaceUrl: true});

    }, err => this.errorService.raise(err));
  }
}
