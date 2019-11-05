import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Result } from '../../models/result';

@Component({
  selector: 'app-payment-confirm',
  templateUrl: './payment-confirm.component.html',
  styleUrls: ['./payment-confirm.component.css']
})
export class PaymentConfirmComponent implements OnInit {
  dataFromServer = [];
  constructor(
    private router: Router,
    private http: HttpClient,
    private authService: AuthService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.dataFromServer = [];
    const userId = localStorage.getItem('userId');
    const param = {
      userId,
      status : 2
    };
    this.http
      .post<Result>(this.authService.basePath + '/training/api/training/v1/findConfirm', param, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          data.forEach(element => {
            this.dataFromServer.push(element);
          });
        }
      });
  }

  confirm(item) {
    console.log(item);
    item.status = 3;
    this.http
      .post<Result>(this.authService.basePath + '/training/api/training/v1/save', item, this.authService.httpOptions)
      .subscribe(response => {
        console.log(response);
        if (response.code === this.authService.successCode) {
          const data = response.data;
          console.log(data);
          this.initData();
        }
      });
  }

}
