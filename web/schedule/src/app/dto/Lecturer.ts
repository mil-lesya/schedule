import {Department} from './Department';
import {Pass} from './Pass';

export class Lecturer {
  id: number;
  department: Department;
  pass: Pass;
  surname: string;
  name: string;
  patronymic: string;
  phoneNumber: string;
  email: string;
  password: string;
}
