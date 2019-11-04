import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Result } from '../../models/result';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {

  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService) { }

  model = {};
  signupMentor = false;
  skills = [];

  ngOnInit() {
    this.model['userType'] = 0;
    this.refreshSkill();
  }

  onChange() {
    if (this.model['userType'] === 0) {
      this.signupMentor = false;
    } else {
      this.signupMentor = true;
    }
  }
  
  refreshSkill() {
    this.http
      .post<Result>(this.authService.basePath + '/search/api/search/v1/findAllSkills', null, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.skills.push(element);
          });
        }
      });
  }
  signup() {
    this.http
      .post<Result>(this.authService.basePath + '/search/api/search/v1/signup', this.model, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.skills.push(element);
          });
        }
      });
  }
}
