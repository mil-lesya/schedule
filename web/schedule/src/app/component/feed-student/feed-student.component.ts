import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {GradebookService} from '../../service/gradebook.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Student} from '../../dto/Student';
import {StudentProviderService} from '../../service/student.provider.service';
import {StudentService} from '../../service/student.service';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-feed-student',
  templateUrl: './feed-student.component.html',
  styleUrls: ['./feed-student.component.scss']
})
export class FeedStudentComponent implements OnInit {

  me: Student;

  constructor(
    private app: AppComponent,
    private router: Router,
    private route: ActivatedRoute,
    private urlService: UrlService,
    private gradebookService: GradebookService,
    private tokenProviderService: TokenProviderService,
    private studentService: StudentService,
    private studentProviderService: StudentProviderService,
    private errorService: ErrorService) {

  }

  ngOnInit() {

    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.studentService.getStudent(token).subscribe(me => {
        this.me = me;
        console.log(this.me);
      });
    },
      err => this.errorService.raise(err));
  }

}
