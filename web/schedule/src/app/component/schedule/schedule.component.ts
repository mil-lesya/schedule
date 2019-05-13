import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {GroupService} from '../../service/group.service';
import {GradebookService} from '../../service/gradebook.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {ScheduleService} from '../../service/schedule.service';
import {Schedule} from '../../dto/Schedule';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {

  schedule: Schedule[];

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private scheduleService: ScheduleService,
              private tokenProviderService: TokenProviderService) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.scheduleService.getSchedule(token).subscribe(schedule => {
        this.schedule = schedule;
      });
    });
  }


}
