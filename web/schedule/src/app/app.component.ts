import { Component } from '@angular/core';
import {LOCALSTORAGE_TOKEN_NAME} from '../global';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {AuthStudentService} from './service/auth.student.service';
import {UrlService} from './service/url.service';
import {TokenProviderService} from './service/token.provider.service';
import {StudentProviderService} from './service/student.provider.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Schedule';

  isLoaded: boolean = false;


}
