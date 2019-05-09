import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {GroupService} from '../../service/group.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {GradebookService} from '../../service/gradebook.service';
import {Assessment} from '../../dto/Assessment';
import {EditAssessment} from '../../dto/EditAssessment';
import {Student} from '../../dto/Student';
import {RegisterStudent} from '../../dto/RegisterStudent';

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.scss']
})
export class GradebookComponent implements OnInit {

  assessments: Assessment[];
  isHeadman: boolean;
  studentId: number;

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private urlService: UrlService,
              private gradebookService: GradebookService,
              private tokenProviderService: TokenProviderService) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.gradebookService.getGradebookAssessments(token).subscribe(assessments => {
        this.assessments = assessments;
      });
      this.gradebookService.isHeadman(token).subscribe(isHeadman => {
        this.isHeadman = isHeadman;
        console.log(isHeadman);
        if (isHeadman) {
          this.route.queryParams.subscribe(params => {
            this.studentId = params.id;
            console.log( this.studentId);
            this.gradebookService.getStudentAssessments(params.id).subscribe(assessments => {
              this.assessments = assessments;
              console.log(assessments);
            });
          });

        }
      });
    });
  }

  save(){
    console.log(this.assessments);
  }
}
