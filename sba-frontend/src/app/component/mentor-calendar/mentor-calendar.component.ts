import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Result } from '../../models/result';
import { AddCalendarComponent } from '../add-calendar/add-calendar.component';
import { MatDatepicker } from '@angular/material/';

@Component({
  selector: 'app-mentor-calendar',
  templateUrl: './mentor-calendar.component.html',
  styleUrls: ['./mentor-calendar.component.css']
})
export class MentorCalendarComponent implements OnInit {

  calendars = [];
  displayName = '';

  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    private matDialog: MatDialog) { }

  ngOnInit() {
    this.initData();
    this.displayName = localStorage.getItem('displayName');
  }

  initData() {
    const userId = localStorage.getItem('userId');
    const param = {
      mid : userId
    };
    this.http
      .post<Result>(this.authService.basePath + '/user/api/user/v1/findMentorCalendar', param, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.calendars.push(element);
          });
        }
      });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['search']);
  }

  showDialog() {
    const dialogConfig = new MatDialogConfig();
    const dialogRef = this.matDialog.open(AddCalendarComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(value => {
      const userId = localStorage.getItem('userId');
      value.mid = userId;
      value.startTime = value.startTime + ':00';
      value.endTime = value.endTime + ':00';
      console.log(value);
      this.http
        .post<Result>(this.authService.basePath + '/user/api/user/v1/saveMentorCalendar', value, this.authService.httpOptions)
        .subscribe(response => {
          console.log(response);
          if (response.code === this.authService.successCode) {
            const data = response.data;
            console.log(data);
            this.calendars.push(data);
          }
        });
    });
  }
}
