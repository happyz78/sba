import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { SearchComponent } from './component/search/search.component';
import { LoginComponent } from './component/login/login.component';
import { TrainingsComponent } from './component/trainings/trainings.component';
import { SingupComponent } from './component/singup/singup.component';
import { MentorCalendarComponent } from './component/mentor-calendar/mentor-calendar.component';
import { MentorAuthGuard } from './guards/mentorAuth.guard';

const routes: Routes = [
  {
    path: 'search',
    // canActivate: [AuthGuard],
    component: SearchComponent
  },
  {
    path: 'mentorCalendar',
    canActivate: [MentorAuthGuard],
    component: MentorCalendarComponent
  },
  {
    path: 'trainings',
    canActivate: [AuthGuard],
    component: TrainingsComponent
  },
  {
    path: 'signup',
    component: SingupComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: '', redirectTo: '/search', pathMatch: 'full' },
  { path: '**', redirectTo: '/search' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
