export class RegisterLecturer {
  passNumber: string;
  surname: string;
  name: string;
  patronymic: string;
  phoneNumber: string;
  email: string;
  auditory: string;
  corpus: string;
  password: string;


  constructor(passNumber: string, surname: string, name: string, patronymic: string, phoneNumber: string, mail: string, auditory: string, corpus: string, password: string) {
    this.passNumber = passNumber;
    this.surname = surname;
    this.name = name;
    this.patronymic = patronymic;
    this.phoneNumber = phoneNumber;
    this.email = mail;
    this.auditory = auditory;
    this.corpus = corpus;
    this.password = password;
  }
}
