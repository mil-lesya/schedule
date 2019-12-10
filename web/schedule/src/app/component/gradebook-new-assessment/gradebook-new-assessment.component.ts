import {Component, OnInit} from '@angular/core';
import {Assessment} from "../../dto/Assessment";
import {NewAssessment} from "../../dto/NewAssessment";
import {AppComponent} from "../../app.component";
import {ActivatedRoute, Router} from "@angular/router";
import {GradebookService} from "../../service/gradebook.service";
import {TokenProviderService} from "../../service/token.provider.service";
import {AssessmentService} from "../../service/assessment.service";
import {ErrorService} from "../../service/error.service";

@Component({
  selector: 'app-gradebook-new-assessment',
  templateUrl: './gradebook-new-assessment.component.html',
  styleUrls: ['./gradebook-new-assessment.component.scss']
})
export class GradebookNewAssessmentComponent implements OnInit {

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
      this.route.queryParams.subscribe(params => {
        this.studentId = params.id;
        console.log(this.studentId);
        this.gradebookService.getStudentAssessments(params.id, this.token).subscribe(assessments => {
          this.assessments = assessments;
          this.isLecturer = true;
          console.log('lect' + this.isLecturer);
          console.log(assessments);
        }, err => this.errorService.raise(err));
      }, err => this.errorService.raise(err));
    }, err => this.errorService.raise(err));
  }


  onSubmit(): void {
    if (this.buttonType === 'add') {
      this.received = true;
        this.newAssessment.studentId = this.studentId;
        console.log(this.newAssessment);
    } else if (this.buttonType === 'save') {
      this.assessmentService.addAssessment(this.newAssessment, this.token).subscribe(() => {
        this.gradebookService.getStudentAssessments(this.studentId, this.token).subscribe(assessments => {
          this.assessments = assessments;
          console.log(assessments);
          this.received = false;
          this.newAssessment = new NewAssessment();
        });
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'delete') {
      this.assessmentService.deleteAssessment(this.assessmentId, this.token).subscribe(() => {
        console.log('delete');
        this.gradebookService.getStudentAssessments(this.studentId, this.token).subscribe(assessments => {
          this.assessments = assessments;
        });
      }, err => this.errorService.raise(err));
    }

  }

  takeAssessmentId(assessmentId) {
    this.assessmentId = assessmentId;
  }
}
