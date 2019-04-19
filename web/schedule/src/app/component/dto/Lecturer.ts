import {Department} from "./Department";
import {Pass} from "./Pass";

export class Lecturer {
  id: number;
  department : Department;
  pass : Pass;
  surname : string;
  name : string;
  patronimic : string;
  phoneNumber : string;
  mail : string;
  password : string;
}
