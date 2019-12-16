import {Component, Inject, OnInit} from '@angular/core';
import {ExpectedGroup} from '../../dto/ExpectedGroup';
import {Schedule} from '../../dto/Schedule';
import {TokenProviderService} from '../../service/token.provider.service';
import {ScheduleService} from '../../service/schedule.service';
import {AuthAdminService} from '../../service/auth.admin.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ErrorService} from '../../service/error.service';
import {sortBy} from "sort-by-typescript";
import {SubjectService} from "../../service/subject.service";
import {Subject} from "../../dto/Subject";
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-admin-schedule',
  templateUrl: './admin-schedule.component.html',
  styleUrls: ['./admin-schedule.component.scss']
})
export class AdminScheduleComponent implements OnInit {

  expectedGroup: ExpectedGroup = new ExpectedGroup();
  authorize: boolean;
  received: boolean;
  schedule: Schedule[] = [];
  monday: Schedule[] = [];
  tuesday: Schedule[] = [];
  wednesday: Schedule[] = [];
  thursday: Schedule[] = [];
  friday: Schedule[] = [];
  saturday: Schedule[] = [];
  time: string[] = ['8:00', '9:50', '11:40', '13:50', '15:40'];
  token: string;
  buttonType: string;
  view: boolean;
  subject: string;
  subjects: Subject[]=[];
  search: boolean;

  constructor(
    @Inject(DOCUMENT) document,
    private tokenProviderService: TokenProviderService,
    private scheduleService: ScheduleService,
    private authAdminService: AuthAdminService,
    private router: Router,
    private route: ActivatedRoute,
    private subjectService: SubjectService,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
        console.log(token);
        this.token = token;
        this.authorize = true;
      }, err => this.errorService.raise(err)
    );
  }

  getSchedule() {
    this.schedule = [];
    this.monday = [];
    this.tuesday = [];
    this.wednesday = [];
    this.thursday = [];
    this.friday = [];
    this.saturday = [];
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
        this.received = true;
        console.log(this.schedule);
      },
      err => this.errorService.raise(err));
  }

  onSubmit() {
    if (this.buttonType === 'view') {
      console.log(this.schedule);
      this.view = true;
      this.getSchedule();
      console.log(this.view);
      console.log(this.schedule);
    } else if (this.buttonType === 'save') {
      this.view = false;
      console.log(this.view);
      this.schedule = this.schedule.concat(this.monday, this.tuesday, this.wednesday, this.thursday, this.friday, this.saturday);
      console.log(this.schedule);
      this.scheduleService.saveSchedule(this.schedule, this.token).subscribe(() => {

        this.getSchedule();
        console.log(this.schedule);
      }, err => this.errorService.raise(err));

    }
  }

  searchSubject() {
    this.subjectService.searchSubject(this.subject, this.token).subscribe(subjects => {
      console.log('subjects '+ subjects);
      this.subjects = subjects;
      this.search=true;
      document.getElementById('id01').style.display='block';
    })
  }

  modalSubject(){
    document.getElementById('id01').style.display='none';
    this.search=false;
  }
}

