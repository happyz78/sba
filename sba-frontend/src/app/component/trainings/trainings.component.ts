import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Result } from '../../models/result';
import { PaymentComponent } from '../payment/payment.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.css']
})
export class TrainingsComponent implements OnInit {

  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    private matDialog: MatDialog) {
  }
  mentor = {};
  skills = [];
  trainings = [];
  proposalTraining = [];

  ngOnInit() {
    this.mentor = JSON.parse(localStorage.getItem('mentor'));
    this.skills = JSON.parse(localStorage.getItem('skills'));
    this.getTrainings();
    this.getProposalTraings();
  }

  getTrainings() {
    console.log(this.mentor);
    const param = {
      mentorId  : this.mentor['mentor']['user']['id'],
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
    }
  getProposalTraings() {
      const param = {
        mentorId  : this.mentor['mentor']['user']['id'],
        userId : localStorage.getItem('userId')
      };

    this.http
    .post<Result>(this.authService.basePath + '/training/api/training/v1/findTrainingsByUserId',
    param, this.authService.httpOptions)
    .subscribe(response => {
      console.log(response);
      if (response.code === this.authService.successCode) {
        const data = response.data;
        console.log(data);
        data.forEach((element: any) => {
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
    this.http
      .post<Result>(this.authService.basePath + '/training/api/training/v1/save',
      param, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
        }
      });
  }

  checkStatus(item): number {
    let flg = -1;
    console.log(item);
    this.proposalTraining.forEach(value => {
      if (value.startTime === item.startTime && value.endTime === item.endTime) {
        flg = value.status;
      }
    });
    return flg;
  }

  showPayment(item) {
    const dialogConfig = new MatDialogConfig();
    const dialogRef = this.matDialog.open(PaymentComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(value => {
      if (!value) {
        return;
      }
      let training = {};
      this.proposalTraining.forEach(itemTraining => {
        if (itemTraining.startTime === item.startTime && itemTraining.endTime === item.endTime) {
          training = itemTraining;
        }
      });

      training['status'] = 2;
      this.http
        .post<Result>(this.authService.basePath + '/training/api/training/v1/save', training, this.authService.httpOptions)
        .subscribe(response => {
          if (response.code === this.authService.successCode) {
            const data = response.data;
            console.log(data);
            this.getProposalTraings();
          }
        });
    });
  }
}
