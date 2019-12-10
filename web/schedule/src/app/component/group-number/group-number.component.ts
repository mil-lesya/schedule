import {Component, OnInit} from '@angular/core';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {GroupService} from '../../service/group.service';
import {Student} from '../../dto/Student';
import {TokenProviderService} from '../../service/token.provider.service';
import {LecturerService} from '../../service/lecturer.service';
import {ErrorService} from '../../service/error.service';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-group-number',
  templateUrl: './group-number.component.html',
  styleUrls: ['./group-number.component.scss']
})
export class GroupNumberComponent implements OnInit {

  expectedGroup: ExpectedGroup = new ExpectedGroup();
  students: Student[];
  received: boolean;
  token: string;
  authorize: boolean;

  constructor(
    private groupService: GroupService,
    private tokenProviderService: TokenProviderService,
    private lecturerService: LecturerService,
    private errorService: ErrorService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
        console.log(token);
        this.token = token;
      });
    };


  getGroup() {
    this.groupService.getExpectedGroup(this.expectedGroup, this.token).subscribe(students => {
      this.students = JSON.parse(students.toString());
      console.log(this.students);
      this.received = true;
    }, err => this.errorService.raise(err));
  }

  putAssessment(studentId: number) {
    this.router.navigate(['/assessment/new'], {queryParams: {id: studentId}});
  }
}
