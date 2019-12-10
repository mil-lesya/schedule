import {Component, OnInit} from '@angular/core';
import {VerifiableRegisterStudent} from '../../dto/VerifiableRegisterStudent';
import {RegisterStudentService} from '../../service/register.student.service';
import {Router} from '@angular/router';
import {RegisterStudent} from '../../dto/RegisterStudent';
import {ErrorService} from '../../service/error.service';

@Component({
  selector: 'app-register-student',
  templateUrl: './register-student.component.html',
  styleUrls: ['./register-student.component.scss']
})
export class RegisterStudentComponent implements OnInit {

  verifiableRegisterStudent: VerifiableRegisterStudent = new VerifiableRegisterStudent();

  constructor(
    private registerStudentService: RegisterStudentService,
    private router: Router,
    private errorService: ErrorService
  ) {
  }

  ngOnInit() {
  }

  register() {
    if (this.verifiableRegisterStudent.password !== this.verifiableRegisterStudent.confirmPassword) {
      alert('неверный повторный пароль');
      return;
    }

    const registerStudent = new RegisterStudent(
      this.verifiableRegisterStudent.name,
      this.verifiableRegisterStudent.surname,
      this.verifiableRegisterStudent.patronymic,
      this.verifiableRegisterStudent.parentContact,
      this.verifiableRegisterStudent.address,
      this.verifiableRegisterStudent.phoneNumber,
      this.verifiableRegisterStudent.email,
      this.verifiableRegisterStudent.gradebookNumber,
      this.verifiableRegisterStudent.groupNumber,
      this.verifiableRegisterStudent.course,
      this.verifiableRegisterStudent.password);

    this.registerStudentService.register(registerStudent).subscribe(() => {
      this.router.navigate(['/auth/student'], {replaceUrl: true});
    }, err => this.errorService.raise(err));
  }

}
