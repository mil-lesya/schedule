import {Component, OnInit} from '@angular/core';
import {TokenProviderService} from '../../service/token.provider.service';
import {AttendanceService} from '../../service/attendance.service';
import {Attendance} from '../../dto/Attendance';

@Component({
  selector: 'app-presence',
  templateUrl: './presence.component.html',
  styleUrls: ['./presence.component.scss']
})
export class PresenceComponent implements OnInit {

  attendance: Attendance[];

  constructor(
    private tokenProviderService: TokenProviderService,
    private attendanseService: AttendanceService,
  ) {
  }

  ngOnInit() {
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.attendanseService.getAttendance(token).subscribe(attendance => {
        this.attendance = attendance;
        console.log(attendance);
      });
    });
  }

}
