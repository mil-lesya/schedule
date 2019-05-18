import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {VerifiableRegisterLecturer} from '../../dto/VerifiableRegisterLecturer';
import {RegisterLecturerService} from '../../service/register.lecturer.service';
import {RegisterLecturer} from '../../dto/RegisterLecturer';

@Component({
  selector: 'app-register-lecturer',
  templateUrl: './register-lecturer.component.html',
  styleUrls: ['./register-lecturer.component.scss']
})
export class RegisterLecturerComponent implements OnInit {

  verifiableRegisterLecturer: VerifiableRegisterLecturer = new VerifiableRegisterLecturer();

  constructor(
    private registerStudentService: RegisterLecturerService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  register() {
    if (this.verifiableRegisterLecturer.password !== this.verifiableRegisterLecturer.confirmPassword) {
      return;
    }

    const registerLecturer = new RegisterLecturer(
      this.verifiableRegisterLecturer.passNumber,
      this.verifiableRegisterLecturer.surname,
      this.verifiableRegisterLecturer.name,
      this.verifiableRegisterLecturer.patronymic,
      this.verifiableRegisterLecturer.phoneNumber,
      this.verifiableRegisterLecturer.mail,
      this.verifiableRegisterLecturer.auditory,
      this.verifiableRegisterLecturer.password);

    this.registerStudentService.register(registerLecturer).subscribe(() => {
      this.router.navigate(['/auth/lecturer'], {replaceUrl: true});
    });
  }

}