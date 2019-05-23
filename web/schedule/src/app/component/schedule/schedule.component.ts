import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenProviderService} from '../../service/token.provider.service';
import {ScheduleService} from '../../service/schedule.service';
import {Schedule} from '../../dto/Schedule';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})

export class ScheduleComponent implements OnInit {

  schedule: Schedule[];
  monday: Schedule[] = [];
  tuesday: Schedule[] = [];
  wednesday: Schedule[] = [];
  thursday: Schedule[] = [];
  friday: Schedule[] = [];
  saturday: Schedule[] = [];
  time: string[] = ['8:00', '9:50', '11:40', '13:50', '15:40'];
  came: boolean;

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private scheduleService: ScheduleService,
              private tokenProviderService: TokenProviderService,
              private errorService: ErrorService) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.scheduleService.getSchedule(token).subscribe(schedule => {
        this.schedule = schedule;
        console.log(schedule);
        for (const s of schedule) {
          switch (s.week) {
            case 'MONDAY': {
              this.monday.push(s);
              console.log(this.monday.length);
              break;
            }
            case 'TUESDAY': {
              this.tuesday.push(s);
              break;
            }
            case 'WEDNESDAY': {
              this.wednesday.push(s);
              break;
            }
            case 'THURSDAY': {
              this.thursday.push(s);
              break;
            }
            case 'FRIDAY': {
              this.friday.push(s);
              break;
            }
            case 'SATURDAY': {
              this.saturday.push(s);
              break;
            }
          }
        }
        if (schedule.length > 0) {
          this.came = true;
        }
      }, err => this.errorService.raise(err));
    }, err => this.errorService.raise(err));
  }


}
