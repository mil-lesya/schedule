import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {GradebookService} from '../../service/gradebook.service';
import {Assessment} from '../../dto/Assessment';
import {AssessmentService} from '../../service/assessment.service';
import {NewAssessment} from '../../dto/NewAssessment';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.scss']
})
export class GradebookComponent implements OnInit {

  received: boolean;
  assessments: Assessment[];
  isLecturer: boolean;
  studentId: number;
  buttonType: string;
  newAssessment: NewAssessment = new NewAssessment();
  assessmentId: number;
  token: string;

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private gradebookService: GradebookService,
              private tokenProviderService: TokenProviderService,
              private assessmentService: AssessmentService,
              private errorService: ErrorService) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.token = token;
      this.gradebookService.getGradebookAssessments(token).subscribe(assessments => {
        this.assessments = assessments;
      }, err => this.errorService.raise(err));
    }, err => this.errorService.raise(err));
  }

}
