import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {LecturerService} from '../../service/lecturer.service';
import {Lecturer} from '../../dto/Lecturer';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-feed-lecturer',
  templateUrl: './feed-lecturer.component.html',
  styleUrls: ['./feed-lecturer.component.scss']
})
export class FeedLecturerComponent implements OnInit {

  lecturer: Lecturer;
  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private urlService: UrlService,
              private tokenProviderService: TokenProviderService,
              private lecturerService: LecturerService,
              private errorService: ErrorService) { }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.lecturerService.getLecturer(token).subscribe(lecturer =>
        this.lecturer = lecturer);
      console.log( this.lecturer);
    },
      err => this.errorService.raise(err));
  }

}
