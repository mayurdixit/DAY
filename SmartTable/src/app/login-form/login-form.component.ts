import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
//import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { AlertService } from '../services/alert.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginFormComponent implements OnInit {

  model: any = {};
  loading = false;
  homeUrl = 'home';
  resetPasswordUrl = "login/reset-password";

 // constructor(private router:Router, private user:UserService) { 
  constructor(
    private user:UserService,
    private router: Router,
    private alertService: AlertService) {  }

  ngOnInit() {   
    this.user.logout();
  }



  loginUser(ev) {
    ev.preventDefault();
    console.log("Submited:");
    console.log(ev);
    var username = ev.target.elements[0].value;
    var password = ev.target.elements[1].value;
    console.log(username, password);
    //if(username !== '' && password !== '') {
     // this.user.setUserLoggedIn();  
     this.loading = true;
      this.user.authenticateUser(username, password).subscribe(
        data => {
          console.log("resetPassword = " + data.resetPassword);
          if(data.resetPassword === true){
            this.router.navigate([this.resetPasswordUrl]);
          } else {
            this.router.navigate([this.homeUrl]);
          }
          
        },
        error => {
          console.log("got error");
          console.log(error);
          this.alertService.error("UserName Password incorrect");
          this.loading = false;
        }
      );
  } 
}
