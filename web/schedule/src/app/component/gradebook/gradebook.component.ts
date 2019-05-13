import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {GradebookService} from '../../service/gradebook.service';
import {Assessment} from '../../dto/Assessment';
import {AssessmentService} from '../../service/assessment.service';
import {Observable} from 'rxjs';
import {Subject} from '../../dto/Subject';
import {Session} from '../../dto/Session';
import {NewAssessment} from '../../dto/NewAssessment';

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.scss']
})
export class GradebookComponent implements OnInit {

  assessments: Assessment[];
  isHeadman: boolean;
  studentId: number;
  buttonType: string;
  newAssessments: NewAssessment[] = [];

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private urlService: UrlService,
              private gradebookService: GradebookService,
              private tokenProviderService: TokenProviderService,
              private assessmentService: AssessmentService) {
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
            console.log(this.studentId);
            this.gradebookService.getStudentAssessments(params.id).subscribe(assessments => {
              this.assessments = assessments;
              console.log(assessments);
            });
          });

        }
      });
    });
  }


  onSubmit(): void {
    if (this.buttonType === 'add') {

      console.log(this.newAssessments);
    }
    if (this.buttonType === 'save') {
      console.log(this.assessments);
      this.assessmentService.saveAssessments(this.assessments).subscribe(() => {
        this.assessmentService.addAssessment(this.newAssessments).subscribe(() => {
          this.router.navigate(['/group'], {replaceUrl: true});
        });
      });
    }

  }
}
