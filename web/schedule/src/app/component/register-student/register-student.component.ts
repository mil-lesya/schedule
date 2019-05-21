import {Component, OnInit} from '@angular/core';
import {VerifiableRegisterStudent} from '../../dto/VerifiableRegisterStudent';
import {RegisterStudentService} from '../../service/register.student.service';
import {Router} from '@angular/router';
import {RegisterStudent} from '../../dto/RegisterStudent';

@Component({
  selector: 'app-register-student',
  templateUrl: './register-student.component.html',
  styleUrls: ['./register-student.component.scss']
})
export class RegisterStudentComponent implements OnInit {

  verifiableRegisterStudent: VerifiableRegisterStudent = new VerifiableRegisterStudent();

  constructor(
    private registerStudentService: RegisterStudentService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  register() {
    if (this.verifiableRegisterStudent.password !== this.verifiableRegisterStudent.confirmPassword) {
      return;
    }

    const registerStudent = new RegisterStudent(
      this.verifiableRegisterStudent.name,
      this.verifiableRegisterStudent.surname,
      this.verifiableRegisterStudent.patronymic,
      this.verifiableRegisterStudent.parentContact,
      this.verifiableRegisterStudent.address,
      this.verifiableRegisterStudent.phoneNumber,
      this.verifiableRegisterStudent.mail,
      this.verifiableRegisterStudent.gradebookNumber,
      this.verifiableRegisterStudent.groupNumber,
      this.verifiableRegisterStudent.course,
      this.verifiableRegisterStudent.password);

    this.registerStudentService.register(registerStudent).subscribe(() => {
      this.router.navigate(['/auth/student'], {replaceUrl: true});
    }, error => {
      alert('oops');
    });
  }

}
