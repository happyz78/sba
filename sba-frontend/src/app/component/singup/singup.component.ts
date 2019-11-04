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

  message = '';
  model = {};
  signupMentor = false;
  skills = [];
  showAlert = false;

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
    if (this.checkInput()) {
      this.showAlert = true;
      return;
    }

    const param = {
      userName : this.model['userName']
    };
    this.http
      .post(this.authService.basePath + '/search/api/search/v1/queryUser',
      param, this.authService.httpOptions)
      .subscribe(response => {
        if (response['data']) {
          this.message = 'Duplicate User Name';
          this.showAlert = true;
          return;
        }

        this.http
        .post<Result>(this.authService.basePath + '/search/api/search/v1/signup', this.model, this.authService.httpOptions)
        .subscribe(resp => {
          console.log(resp);
          if (resp.code === this.authService.successCode) {
            this.router.navigate(['login']);
          }
        });
      });


  }

  checkInput(): boolean {
    if (!this.model['userName']) {
      this.message = 'Please input User Name';
      return true;
    }
    if (!this.model['password']) {
      this.message = 'Please input Password';
      return true;
    }
    if (this.model['userType'] === 1) {
      if (!this.model['sid']) {
        this.message = 'Please input Skill';
        return true;
      }
      if (!this.model['yearsOfExperience']) {
        this.message = 'Please input Years Of Experience';
        return true;
      }
      if (!this.model['selfRating']) {
        this.message = 'Please input Self Rating';
        return true;
      }
    }
    return false;
  }
}
