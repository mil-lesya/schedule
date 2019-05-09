import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GradebookComponent} from './component/gradebook/gradebook.component';
import {FeedStudentComponent} from './component/feed-student/feed-student.component';
import {ScheduleComponent} from './component/schedule/schedule.component';
import {GroupComponent} from './component/group/group.component';
import {PresenceComponent} from './component/presence/presence.component';
import {HomeComponent} from './component/home/home.component';
import {AuthStudentComponent} from './component/auth-student/auth-student.component';
import {RegisterStudentComponent} from './component/register-student/register-student.component';
import {AuthLecturerComponent} from './component/auth-lecturer/auth-lecturer.component';
import {RegisterLecturerComponent} from './component/register-lecturer/register-lecturer.component';
import {FeedLecturerComponent} from './component/feed-lecturer/feed-lecturer.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {title: 'Home'}
  },
  {
    path: 'feed/student',
    component: FeedStudentComponent,
    data: {title: 'Feed'}
  },
  {
    path: 'feed/lecturer',
    component: FeedLecturerComponent,
    data: {title: 'Feed'}
  },
  {
    path: 'auth/student',
    component: AuthStudentComponent,
    data: {title: 'Auth'}

  },

  {
    path: 'auth/lecturer',
    component: AuthLecturerComponent,
    data: {title: 'Auth'}
  },

  {
    path: 'gradebook',
    component: GradebookComponent,
    data: {title: 'Gradebook'}
  },

  {
    path: 'gradebook/student',
    component: GradebookComponent,
    data: {title: 'Gradebook'}
  },

  {
    path: 'schedule',
    component: ScheduleComponent,
    data: {title: 'Schedule'}
  },

  {
    path: 'group',
    component: GroupComponent,
    data: {title: 'Group'}
  },

  {
    path: 'presence',
    component: PresenceComponent,
    data: {title: 'Presence'}
  },

  {
    path: 'home',
    component: HomeComponent,
    data: {title: 'Home'}
  },

  {
    path: 'register/student',
    component: RegisterStudentComponent,
    data: {title: 'Register'}
  },

  {
    path: 'register/lecturer',
    component: RegisterLecturerComponent,
    data: {title: 'Register'}
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
