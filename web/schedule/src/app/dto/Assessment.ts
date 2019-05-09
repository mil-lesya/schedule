import {Gradebook} from './Gradebook';
import {Session} from './Session';
import {Sabject} from './Sabject';

export  class Assessment {
  id: number;
  gradebook: Gradebook;
  subject: Sabject;
  session: Session;
  mark: number;
}
