import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginFormComponent implements OnInit {

  constructor(private router:Router, private user:UserService) { }

  ngOnInit() {
  }

  loginUser(ev) {
    ev.preventDefault();
    console.log(ev);
    var username = ev.target.elements[0].value;
    var password = ev.target.elements[1].value;
    console.log(username, password);
    if(username !== '' && password !== '') {
     // this.user.setUserLoggedIn();
      this.user.username = username;
      this.user.authenticateUser(username);
      this.router.navigate(['dashboard']);
    }
    return false;
  } 
}
