export class RegisterStudent {
  name: string;
  surname: string;
  patronymic: string;
  parentContact: string;
  address: string;
  phoneNumber: string;
  email: string;
  gradebookNumber: string;
  groupNumber: number;
  course: number;
  password: string;

  constructor(name: string, surname: string, patronymic: string, parentContact: string, address: string, phoneNumber: string, email: string, gradebookNumber: string, groupNumber: number, course: number, password: string) {
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
    this.parentContact = parentContact;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.gradebookNumber = gradebookNumber;
    this.groupNumber = groupNumber;
    this.course = course;
    this.password = password;
  }
}
