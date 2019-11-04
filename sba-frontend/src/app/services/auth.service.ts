import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { LoginResponse } from '../models/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // API path
  basePath = 'http://localhost:8080/gateway';
  successCode = '00000';

  constructor(
    private router: Router,
    private http: HttpClient
  ) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    //observe: "response" as 'body'
  };


  // Handle API errors
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }


  // Verify user credentials on server to get token
  loginForm(data) {
    return this.http
      .post<LoginResponse>(this.basePath + '/jwt/login', data, this.httpOptions);
  }

  // After login save token and other values(if any) in localStorage
  setUser(resp: LoginResponse) {
    localStorage.setItem('access_token', resp.token);
    const param = {
      userName : resp.name
    };
    this.http
      .post(this.basePath + '/user/api/user/v1/query',
      param, this.httpOptions)
      .subscribe(response => {
        localStorage.setItem('userId', response['data']['id']);
        localStorage.setItem('userType', response['data']['userType']);
        localStorage.setItem('displayName', response['data']['firstName'] + ' ' + response['data']['lastName']);
      });
  }

  // Checking if token is set
  isLoggedIn() {
    return localStorage.getItem('access_token') != null;
  }

  // After clearing localStorage redirect to login screen
  logout() {
    localStorage.clear();
  }


  // Get data from server for Dashboard
  getData(data) {
    this.httpOptions["observe"] = 'response' as 'body';
    this.httpOptions.headers = this.httpOptions.headers.set('content-type', 'application/json');
    console.log(this.httpOptions);
    return this.http
      .post(this.basePath + '/user/api/user/v1/query', data, this.httpOptions);
  }

}
