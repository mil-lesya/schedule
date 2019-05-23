import {Component, OnInit} from '@angular/core';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {Schedule} from '../../dto/Schedule';
import {TokenProviderService} from '../../service/token.provider.service';
import {ScheduleService} from '../../service/schedule.service';
import {AuthAdminService} from '../../service/auth.admin.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Admin} from '../../dto/Admin';
import {error} from '@angular/compiler/src/util';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-admin-schedule',
  templateUrl: './admin-schedule.component.html',
  styleUrls: ['./admin-schedule.component.scss']
})
export class AdminScheduleComponent implements OnInit {

  expectedGroup: ExpectedGroup = new ExpectedGroup();
  authorize: boolean;
  received: boolean;
  schedule: Schedule[];
  monday: Schedule[] = [];
  tuesday: Schedule[] = [];
  wednesday: Schedule[] = [];
  thursday: Schedule[] = [];
  friday: Schedule[] = [];
  saturday: Schedule[] = [];
  time: string[] = ['8:00', '9:50', '11:40', '13:50', '15:40'];
  admin: Admin;
  buttonType: string;
  view: boolean;

  constructor(
    private tokenProviderService: TokenProviderService,
    private scheduleService: ScheduleService,
    private authAdminService: AuthAdminService,
    private router: Router,
    private route: ActivatedRoute,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {

  }

  getSchedule() {
    console.log(this.expectedGroup);
    this.scheduleService.getGroupSchedule(this.expectedGroup).subscribe(schedule => {
        this.schedule = JSON.parse(schedule);
        console.log(this.schedule);
        for (const s of this.schedule) {
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
        this.received = true;
        console.log(this.schedule);
      },
      error1 => this.errorService.raise(error1));
  }

  onSubmit() {
    if (this.buttonType === 'view') {
      this.view = true;
      this.getSchedule();
      console.log(this.view);
    } else if (this.buttonType === 'save') {
      this.view = false;
      console.log(this.view);
      this.schedule = [];
      console.log(this.schedule);
      console.log(this.monday);
      this.schedule = this.schedule.concat(this.monday, this.tuesday, this.wednesday, this.thursday, this.friday, this.saturday);
      console.log(this.schedule);
      this.scheduleService.saveSchedule(this.schedule).subscribe(() => {
        this.schedule = [];
        this.monday = [];
        this.tuesday = [];
        this.wednesday = [];
        this.thursday = [];
        this.friday = [];
        this.saturday = [];
        this.getSchedule();
        console.log(this.schedule);
      },       err => this.errorService.raise(err));

    }
  }
}

