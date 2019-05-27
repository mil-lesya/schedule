import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Admin} from '../../dto/Admin';
import {AuthAdminService} from '../../service/auth.admin.service';
import {LOCALSTORAGE_TOKEN_NAME} from '../../../global';
import {TokenProviderService} from '../../service/token.provider.service';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-auth-admin',
  templateUrl: './auth-admin.component.html',
  styleUrls: ['./auth-admin.component.scss']
})
export class AuthAdminComponent implements OnInit {

  admin: Admin = new Admin();
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
    this.authAdminService.authenticate(this.admin).subscribe(token => {
      this.tokenProviderService.setToken(token);
      localStorage.setItem(LOCALSTORAGE_TOKEN_NAME, token);

      this.router.navigate(['/group/change'], {replaceUrl: true});

    }, err => this.errorService.raise(err));
  }
}
