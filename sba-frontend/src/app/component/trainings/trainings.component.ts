import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Result } from '../../models/result';


@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.css']
})
export class TrainingsComponent implements OnInit {

  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService) {
  }
  mentor = {};
  skills = [];
  trainings = [];
  proposalTraining = [];

  ngOnInit() {
    this.mentor = JSON.parse(localStorage.getItem('mentor'));
    this.skills = JSON.parse(localStorage.getItem('skills'));
    this.getTrainings();
  }

  getTrainings() {
    console.log(this.mentor);
    const param = {
      mentorId  : this.mentor['mentor']['userId'],
      userId : localStorage.getItem('userId')
    };
    console.log(param);
    this.http
      .post<Result>(this.authService.basePath + '/training/api/training/v1/findTrainingsByMentorId',
      param, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.trainings.push(element);
          });
        }
      });

    this.http
    .post<Result>(this.authService.basePath + '/training/api/training/v1/findTrainingsByUserId',
    param, this.authService.httpOptions)
    .subscribe(response => {
      console.log(response);
      if (response.code === this.authService.successCode) {
        const data = response.data;
        console.log(data);
        data.forEach(element => {
          this.proposalTraining.push(element);
        });
      }
    });
  }

  getSkillName(sid) {
    let skillName;
    this.skills.forEach(element => {
      if (element.id === sid) {
        skillName = element.name;
      }
    });
    return skillName;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/search']);
  }

  proposal(training) {
    console.log(training);
    const param = {
          mentorId  : training['mid'],
          userId  : localStorage.getItem('userId'),
          skillId  : this.mentor['sid'],
          status   : 0,
          progress : 0,
          rating : 0,
          startDate : training['startDatetime'],
          endDate : training['endDatetime'],
          startTime : training['startTime'],
          endTime : training['endTime'],
          };
          console.log(param);
    this.http
      .post<Result>(this.authService.basePath + '/training/api/training/v1/save',
      param, this.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
        }
      });
  }

  checkStatus(item): boolean {
    console.log(item);
    let flg = true;
    this.proposalTraining.forEach(value => {
      if (value.startTime === item.startTime && value.endTime === item.endTime) {
        flg = false;
      }
    });
    return flg;
  }
}
