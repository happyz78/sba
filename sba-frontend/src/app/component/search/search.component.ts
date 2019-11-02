import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  model: any = {};

  dataFromServer: any = [];

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.getSomePrivateStuff();
  }

  getSomePrivateStuff() {
    this.model.userName = 'userName1';
    // this.authService.getData(this.model).subscribe(response => {
    //   console.log(response);
    //   const data = response['body'];
    //   if (data && data.code === '00000') {
    //     this.dataFromServer.push(data.data);
    //   }
    // }, error => {
    //   console.error(error);
    //   this.authService.logout();
    // });
    this.dataFromServer.push(this.model);
  }

  logout() {
    this.authService.logout();
  }

  login() {
    this.router.navigate(['login']);
  }
}
