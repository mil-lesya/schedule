import {Student} from './Student';
import {Lecturer} from "./Lecturer";

export class Group {
  id: number;
  groupNumber : number;
  course : number;
  headman : Student;
  curator : Lecturer;
}
