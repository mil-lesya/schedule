import {Component, OnInit} from '@angular/core';
import {Student} from '../../dto/Student';
import {Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {GroupService} from '../../service/group.service';
import {GradebookService} from '../../service/gradebook.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  students: Student[];
  isHeadman: boolean;

  constructor(
    private router: Router,
    private groupService: GroupService,
    private gradebookService: GradebookService,
    private tokenProviderService: TokenProviderService
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
        });
      });
    });
  }

  editAssessment(studentId: number) {

    this.router.navigate(['/gradebook'], { queryParams: {id: studentId }});

  }
}
