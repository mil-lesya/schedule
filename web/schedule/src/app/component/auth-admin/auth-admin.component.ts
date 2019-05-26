import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Admin} from '../../dto/Admin';
import {AuthAdminService} from '../../service/auth.admin.service';

@Component({
  selector: 'app-auth-admin',
  templateUrl: './auth-admin.component.html',
  styleUrls: ['./auth-admin.component.scss']
})
export class AuthAdminComponent implements OnInit {

  admin: Admin = new Admin();
  isAdmin: boolean;

  constructor(
    private authAdminService: AuthAdminService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  authenticate() {
    this.authAdminService.authenticate(this.admin).subscribe(isAdmin => {
        console.log(isAdmin);
        this.isAdmin = JSON.parse(isAdmin.toString());
        if (this.isAdmin !== true) {
          alert('ошибка: невернный логин или пароль, проверьте введённые данные');
          return;
        } else {
          console.log(isAdmin);
          this.router.navigate(['/group/change'], {replaceUrl: true, queryParams: {admin: this.admin}});
        }
      });
  }
}
