import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    debugger;
    this.authService.logout();
  }

  login() {
    this.authService.loginForm(this.model).subscribe(response => {
      console.log(response);
      this.authService.setUser(response);
      this.router.navigate(['search']);
    }, error => {
      console.error(error);
    });
  }

}
