import {Gradebook} from './Gradebook';
import {Session} from './Session';
import {Subject} from './Subject';

export  class Assessment {
  id: number;
  gradebook: Gradebook;
  subject: Subject;
  session: Session;
  mark: number;

}

