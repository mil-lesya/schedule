import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GradebookComponent} from './component/gradebook/gradebook.component';
import {FeedComponent} from './component/feed/feed.component';
import {ScheduleComponent} from './component/schedule/schedule.component';
import {GroupComponent} from './component/group/group.component';
import {PresenceComponent} from './component/presence/presence.component';
import {HomeComponent} from './component/home/home.component';
import {AuthStudentComponent} from './component/auth-student/auth-student.component';
import {AuthComponent} from './component/auth/auth.component';
import {RegisterStudentComponent} from './component/register-student/register-student.component';

const routes: Routes = [
  {
    path: 'feed',
    component: FeedComponent,
    data: {title: 'Feed'}
  },
  {
    path: 'auth/student',
    component: AuthStudentComponent,
    data: {title: 'Auth'}

  },
  {
    path: 'auth',
    component: AuthComponent,
    data: {title: 'Auth'}
  },
  {
    path: 'gradebook',
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
    data: {title: 'Register Student'}
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
