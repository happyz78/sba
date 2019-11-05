import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatDialogModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './component/login/login.component';
import { SearchComponent } from './component/search/search.component';
import {JwtInterceptor} from './interceptor/jwt.interceptor';
import { TrainingsComponent } from './component/trainings/trainings.component';
import { SingupComponent } from './component/singup/singup.component';
import { MentorCalendarComponent } from './component/mentor-calendar/mentor-calendar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddCalendarComponent } from './component/add-calendar/add-calendar.component';
import {MatDatepickerModule, MatInputModule,MatNativeDateModule} from '@angular/material';
import { MentorTrainingComponent } from './component/mentor-training/mentor-training.component';
import { PaymentConfirmComponent } from './component/payment-confirm/payment-confirm.component';
import { PaymentComponent } from './component/payment/payment.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SearchComponent,
    TrainingsComponent,
    SingupComponent,
    MentorCalendarComponent,
    AddCalendarComponent,
    MentorTrainingComponent,
    PaymentConfirmComponent,
    PaymentComponent
  ],
  imports: [
    MatDialogModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDatepickerModule, MatInputModule, MatNativeDateModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddCalendarComponent,
    PaymentComponent]
})
export class AppModule { }
