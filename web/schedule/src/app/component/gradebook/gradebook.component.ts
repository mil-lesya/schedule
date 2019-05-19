import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {GradebookService} from '../../service/gradebook.service';
import {Assessment} from '../../dto/Assessment';
import {AssessmentService} from '../../service/assessment.service';
import {NewAssessment} from '../../dto/NewAssessment';

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.scss']
})
export class GradebookComponent implements OnInit {

  received: boolean;
  assessments: Assessment[];
  isHeadman: boolean;
  studentId: number;
  buttonType: string;
  newAssessment: NewAssessment = new NewAssessment();
  assessmentId: number;

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
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
      this.received = true;
      this.route.queryParams.subscribe(params => {
        this.studentId = params.id;
        this.newAssessment.studentId = this.studentId;
        console.log(this.newAssessment);
      });
    } else if (this.buttonType === 'save') {
      this.assessmentService.addAssessment(this.newAssessment).subscribe(() => {
        this.gradebookService.getStudentAssessments(this.studentId).subscribe(assessments => {
          this.assessments = assessments;
          console.log(assessments);
          this.received = false;
          this.newAssessment = new NewAssessment();
        });
      });
    } else if (this.buttonType === 'delete') {
      this.assessmentService.deleteAssessment(this.assessmentId).subscribe(() => {
        console.log('delete');
        this.gradebookService.getStudentAssessments(this.studentId).subscribe(assessments => {
          this.assessments = assessments;
        });
      });
    }

  }

  takeAssessmentId(assessmentId) {
    this.assessmentId = assessmentId;
  }

}
