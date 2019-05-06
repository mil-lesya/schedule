import { Component, OnInit } from '@angular/core';
import {Student} from '../../dto/Student';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {Title} from '@angular/platform-browser';
import {AuthStudentService} from '../../service/auth.student.service';
import {StudentProviderService} from '../../service/student.provider.service';
import {GroupService} from '../../service/group.service';
import {ArrayService} from '../../service/array.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  me: Student;
  students: Student[];

  constructor(
              private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private urlService: UrlService,
              private groupService: GroupService,
              private tokenProviderService: TokenProviderService,
              private studentProviderService: StudentProviderService,
              ) { }

  ngOnInit() {
          this.tokenProviderService.token.subscribe(token => {
            this.groupService.getGroup(token).subscribe(students => {
                  this.students = students;
            });
          });
  }

}
