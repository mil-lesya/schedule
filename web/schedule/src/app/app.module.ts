import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FeedStudentComponent } from './component/feed-student/feed-student.component';
import { NavigationStudentComponent } from './component/navigation-student/navigation-student.component';
import { GradebookComponent } from './component/gradebook/gradebook.component';
import { ScheduleComponent } from './component/schedule/schedule.component';
import { GroupComponent } from './component/group/group.component';
import { PresenceComponent } from './component/presence/presence.component';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import { HomeComponent } from './component/home/home.component';
import { AuthStudentComponent } from './component/auth-student/auth-student.component';
import { RegisterStudentComponent } from './component/register-student/register-student.component';
import {HttpClientModule} from '@angular/common/http';
import { AuthLecturerComponent } from './component/auth-lecturer/auth-lecturer.component';
import { RegisterLecturerComponent } from './component/register-lecturer/register-lecturer.component';
import { NavigationLecturerComponent } from './component/navigation-lecturer/navigation-lecturer.component';
import { FeedLecturerComponent } from './component/feed-lecturer/feed-lecturer.component';
import { GroupNumberComponent } from './component/group-number/group-number.component';

@NgModule({
  declarations: [
    AppComponent,
    FeedStudentComponent,
    NavigationStudentComponent,
    GradebookComponent,
    ScheduleComponent,
    GroupComponent,
    PresenceComponent,
    HomeComponent,
    AuthStudentComponent,
    RegisterStudentComponent,
    AuthLecturerComponent,
    RegisterLecturerComponent,
    NavigationLecturerComponent,
    FeedLecturerComponent,
    GroupNumberComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
