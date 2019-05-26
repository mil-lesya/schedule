import {Component, OnInit} from '@angular/core';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {GroupService} from '../../service/group.service';
import {Student} from '../../dto/Student';
import {TokenProviderService} from '../../service/token.provider.service';
import {LecturerService} from '../../service/lecturer.service';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-group-number',
  templateUrl: './group-number.component.html',
  styleUrls: ['./group-number.component.scss']
})
export class GroupNumberComponent implements OnInit {

  expectedGroup: ExpectedGroup = new ExpectedGroup();
  students: Student[];
  received: boolean;
  authorize: boolean;

  constructor(
    private groupService: GroupService,
    private tokenProviderService: TokenProviderService,
    private lecturerService: LecturerService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.lecturerService.authorize(token).subscribe(authorize => {
          this.authorize = authorize;
        }, err => this.errorService.raise(err)
      );
    });
  }

  getGroup() {
    this.groupService.getExpectedGroup(this.expectedGroup).subscribe(students => {
      this.students = JSON.parse(students.toString());
      console.log(this.students);
      this.received = true;
    }, err => this.errorService.raise(err));
  }
}
