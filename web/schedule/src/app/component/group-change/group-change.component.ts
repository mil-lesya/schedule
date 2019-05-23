import {Component, OnInit} from '@angular/core';
import {GroupService} from '../../service/group.service';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {Student} from '../../dto/Student';
import {NewAssessment} from '../../dto/NewAssessment';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {GradebookService} from '../../service/gradebook.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {AssessmentService} from '../../service/assessment.service';
import {NewStudent} from '../../dto/NewStudent';
import {StudentService} from '../../service/student.service';
import {AuthAdminService} from '../../service/auth.admin.service';
import {Admin} from '../../dto/Admin';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-group-change',
  templateUrl: './group-change.component.html',
  styleUrls: ['./group-change.component.scss']
})
export class GroupChangeComponent implements OnInit {

  expectedGroup: ExpectedGroup = new ExpectedGroup();
  students: Student[];
  received: boolean;
  buttonType: string;
  newStudent: NewStudent = new NewStudent();
  studentId: number;
  headmanId: number;

  constructor(
    private groupService: GroupService,
    private app: AppComponent,
    private router: Router,
    private route: ActivatedRoute,
    private gradebookService: GradebookService,
    private authAdminService: AuthAdminService,
    private studentService: StudentService,
    private errorService: ErrorService) {
  }

  ngOnInit() {

  }

  onSubmit(): void {
    if (this.buttonType === 'add') {
      this.received = true;
      this.newStudent.course = this.expectedGroup.course;
      this.newStudent.group = this.expectedGroup.group;
      console.log(this.newStudent);
    } else if (this.buttonType === 'save') {
      this.studentService.saveStudent(this.newStudent).subscribe(() => {
        this.groupService.getExpectedGroup(this.expectedGroup).subscribe(students => {
          this.students = JSON.parse(students);
          console.log(this.students);
          this.newStudent = new NewStudent();
        });
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'delete') {
      this.studentService.deleteStudent(this.studentId).subscribe(() => {
        console.log(this.studentId);
        this.groupService.getExpectedGroup(this.expectedGroup).subscribe(students => {
          this.students = JSON.parse(students);
          console.log(this.students);
        });
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'headman') {
      this.studentService.appointHeadman(this.studentId).subscribe(() => {
        console.log(this.studentId);
        this.headmanId = this.studentId;
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'view') {
      this.groupService.getExpectedGroup(this.expectedGroup).subscribe(students => {
        this.groupService.getHeadmanId(this.expectedGroup).subscribe(headmanId => {
          this.headmanId = headmanId;
          this.students = JSON.parse(students);
          console.log(this.students);
          this.received = true;
        }, error1 => this.errorService.raise(error1));
      }, error => this.errorService.raise(error));
    }

  }

  getStudentId(studentid: number) {
    this.studentId = studentid;
  }
}
