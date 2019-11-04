import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { Router } from '@angular/router';
import { Result } from '../../models/result';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  showAlert = false;
  isLogin = false;
  isMentor = false;
  model: any = {};

  dataFromServer: any = [];

  skills: any = [];
  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService
  ) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    //observe: "response" as 'body'
  };

  ngOnInit() {
    this.refreshSkill();
    this.isLogin = this.authService.isLoggedIn();
    const userType = localStorage.getItem('userType');
    if (userType === '1' || userType === '2') {
      this.isMentor = true;
    }
  }

  refreshSkill() {
    this.http
      .post<Result>(this.authService.basePath + '/search/api/search/v1/findAllSkills', null, this.httpOptions)
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

  search() {
    if (!this.model.sid) {
      this.showAlert = true;
      return;
    }
    this.showAlert = false;
    this.http
      .post<Result>(this.authService.basePath + '/search/api/search/v1/findMentors', this.model, this.httpOptions)
      .subscribe(response => {
        console.log(response);
        this.dataFromServer = [];
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.dataFromServer.push(element);
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
    this.isLogin = false;
  }

  login() {
    this.router.navigate(['login']);
    this.isLogin = this.authService.isLoggedIn();
  }

  goTraining(item) {
    localStorage.setItem('mentor', JSON.stringify(item));
    localStorage.setItem('skills', JSON.stringify(this.skills));
    this.router.navigate(['/trainings']);
  }
}
