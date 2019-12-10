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
import {GroupNumberComponent} from './component/group-number/group-number.component';
import {GroupScheduleComponent} from './component/group-schedule/group-schedule.component';
import {AuthAdminComponent} from './component/auth-admin/auth-admin.component';
import {GroupChangeComponent} from './component/group-change/group-change.component';
import {AdminScheduleComponent} from './component/admin-schedule/admin-schedule.component';
import {GradebookNewAssessmentComponent} from "./component/gradebook-new-assessment/gradebook-new-assessment.component";

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
    data: {title: 'Login.ts'}

  },

  {
    path: 'auth/lecturer',
    component: AuthLecturerComponent,
    data: {title: 'Login.ts'}
  },
  {
    path: 'auth/admin',
    component: AuthAdminComponent,
    data: {title: 'Login.ts'}
  },

  {
    path: 'gradebook',
    component: GradebookComponent,
    data: {title: 'Gradebook'}
  },

  {
    path: 'assessment/new',
    component: GradebookNewAssessmentComponent,
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
    path: 'group/number',
    component: GroupNumberComponent,
    data: {title: 'Group Number'}
  },

  {
    path: 'group/change',
    component: GroupChangeComponent,
  },

  {
    path: 'group/schedule',
    component: GroupScheduleComponent,
    data: {title: 'Group Number'}
  },

  {
    path: 'presence',
    component: PresenceComponent,
    data: {title: 'Presence'}
  },

  {
    path: 'admin/schedule',
    component: AdminScheduleComponent,
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
