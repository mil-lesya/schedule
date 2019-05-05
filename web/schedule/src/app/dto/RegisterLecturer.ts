export class RegisterLecturer {
  passNumber: string;
  surname: string;
  name: string;
  patronymic: string;
  phoneNumber: string;
  mail: string;
  auditory: number;
  password: string;

  constructor(passNumber: string, surname: string, name: string, patronymic: string, phoneNumber: string, mail: string,  auditory: number, password: string) {
    this.passNumber = passNumber;
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
    this.phoneNumber = phoneNumber;
    this.mail = mail;
    this.auditory = auditory;
    this.password = password;
  }
}
