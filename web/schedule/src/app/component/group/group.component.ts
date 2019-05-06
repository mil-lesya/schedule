import {Component, OnInit} from '@angular/core';
import {Student} from '../../dto/Student';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {GroupService} from '../../service/group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.scss']
})
export class GroupComponent implements OnInit {

  num: number;
  students: Student[];

  constructor(
    private app: AppComponent,
    private router: Router,
    private route: ActivatedRoute,
    private urlService: UrlService,
    private groupService: GroupService,
    private tokenProviderService: TokenProviderService
  ) {
  }

  ngOnInit() {
    this.num = 0;
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.groupService.getGroup(token).subscribe(students => {
        this.students = students;
      });
    });
  }

}
