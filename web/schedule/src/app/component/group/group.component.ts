import {Component, OnInit} from '@angular/core';
import {Student} from '../../dto/Student';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {GroupService} from '../../service/group.service';
import {GradebookService} from '../../service/gradebook.service';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  students: Student[];
  isHeadman: boolean;
  isAdmin: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private groupService: GroupService,
    private gradebookService: GradebookService,
    private tokenProviderService: TokenProviderService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.groupService.getGroup(token).subscribe(students => {
        this.students = students;
        this.gradebookService.isHeadman(token).subscribe(isHeadman => {
          this.isHeadman = isHeadman;
          console.log(isHeadman);
        }, err => this.errorService.raise(err));
      }, err => this.errorService.raise(err));
    });
    this.route.queryParams.subscribe(params => {
      this.isAdmin = params.admin;
    });
  }

  setPasses(studentId: number, studentName: string) {
    this.router.navigate(['/presence'], {queryParams: {id: studentId, name: studentName}});
  }
}
