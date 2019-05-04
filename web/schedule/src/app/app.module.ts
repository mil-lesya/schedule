import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FeedComponent } from './component/feed/feed.component';
import { NavigationComponent } from './component/navigation/navigation.component';
import { GradebookComponent } from './component/gradebook/gradebook.component';
import { ScheduleComponent } from './component/schedule/schedule.component';
import { GroupComponent } from './component/group/group.component';
import { PresenceComponent } from './component/presence/presence.component';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import { HomeComponent } from './component/home/home.component';
import { AuthStudentComponent } from './component/auth-student/auth-student.component';
import { RegisterStudentComponent } from './component/register-student/register-student.component';
import { AuthComponent } from './component/auth/auth.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    FeedComponent,
    NavigationComponent,
    GradebookComponent,
    ScheduleComponent,
    GroupComponent,
    PresenceComponent,
    HomeComponent,
    AuthStudentComponent,
    RegisterStudentComponent,
    AuthComponent,
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
