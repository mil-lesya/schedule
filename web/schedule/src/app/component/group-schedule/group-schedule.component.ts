import {Component, OnInit} from '@angular/core';
import {TokenProviderService} from '../../service/token.provider.service';
import {LecturerService} from '../../service/lecturer.service';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {ScheduleService} from '../../service/schedule.service';
import {Schedule} from '../../dto/Schedule';
import {ErrorService} from '../../service/error.service';
import {sortBy} from "sort-by-typescript";

@Component({
  selector: 'app-group-schedule',
  templateUrl: './group-schedule.component.html',
  styleUrls: ['./group-schedule.component.scss']
})
export class GroupScheduleComponent implements OnInit {

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
  token: string;

  constructor(
    private tokenProviderService: TokenProviderService,
    private lecturerService: LecturerService,
    private scheduleService: ScheduleService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.token = token;
    }, err => this.errorService.raise(err));
  }

  getSchedule() {
    console.log(this.expectedGroup);
    this.scheduleService.getGroupSchedule(this.expectedGroup, this.token).subscribe(schedule => {
      this.schedule = JSON.parse(schedule.toString()).sort(sortBy('classNumber'));
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
      console.log(this.schedule);
      this.received = true;
    }, err => this.errorService.raise(err));
  }

}
