import {Component, OnInit} from '@angular/core';
import {TokenProviderService} from '../../service/token.provider.service';
import {AttendanceService} from '../../service/attendance.service';
import {Attendance} from '../../dto/Attendance';
import {GradebookService} from '../../service/gradebook.service';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {NewAttendance} from '../../dto/NewAttendance';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-presence',
  templateUrl: './presence.component.html',
  styleUrls: ['./presence.component.scss']
})
export class PresenceComponent implements OnInit {

  received: boolean;
  attendance: Attendance[];
  isHeadman: boolean;
  buttonType: string;
  studentName: string;
  studentId: number;
  attendanceId: number;
  newAttendance: NewAttendance = new NewAttendance();
  token: string;

  constructor(
    private app: AppComponent,
    private router: Router,
    private route: ActivatedRoute,
    private tokenProviderService: TokenProviderService,
    private attendanceService: AttendanceService,
    private gradebookService: GradebookService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.token = token;
      this.attendanceService.getAttendance(token).subscribe(attendance => {
        this.attendance = attendance;
        console.log(attendance);
        this.gradebookService.isHeadman(token).subscribe(isHeadman => {
          this.isHeadman = isHeadman;
          console.log(isHeadman);
          if (isHeadman) {
            this.route.queryParams.subscribe(params => {
              this.studentId = params.id;
              this.studentName = params.name;
              console.log(this.studentId);
              this.attendanceService.getStudentAttendance(this.studentId).subscribe(attendance  => {
                this.attendance = attendance;
                console.log(attendance);
              }, err => this.errorService.raise(err));
            }, err => this.errorService.raise(err));
          }
        }, err => this.errorService.raise(err));
      }, err => this.errorService.raise(err));
    }, err => this.errorService.raise(err));
  }

  onSubmit(): void {
    if (this.buttonType === 'add') {
      this.received = true;
      this.route.queryParams.subscribe(params => {
        this.studentId = params.id;
        this.newAttendance.studentId = this.studentId;
        console.log(this.newAttendance);
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'save') {
      this.attendanceService.addAttendance(this.newAttendance, this.token).subscribe(() => {
        this.attendanceService.getStudentAttendance(this.studentId).subscribe(attendance => {
          this.attendance = attendance;
          console.log(attendance);
          this.received = false;
          this.newAttendance = new NewAttendance();
        }, err => this.errorService.raise(err));
      }, err => this.errorService.raise(err));
    } else if (this.buttonType === 'delete') {
      this.attendanceService.deleteAttendance(this.attendanceId, this.token).subscribe(() => {
        console.log('delete');
        this.attendanceService.getStudentAttendance(this.studentId).subscribe(attendance => {
          this.attendance = attendance;
        }, err => this.errorService.raise(err));
      }, err => this.errorService.raise(err));
    }

  }

  takeAttendanceId(attendanceId) {
    this.attendanceId = attendanceId;
  }
}
