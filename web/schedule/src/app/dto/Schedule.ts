import {Auditory} from './Auditory';
import {Subject} from './Subject';
import {Lecturer} from './Lecturer';
import {Group} from './Group';
import {Week} from './Week';

export  class Schedule {
  subjectDto: Subject;
  auditoryDto: Auditory;
  lecturerDto: Lecturer;
  classNumber: number;
  periodicity: number;
  week: Week;
  groupList: Group[];
}
