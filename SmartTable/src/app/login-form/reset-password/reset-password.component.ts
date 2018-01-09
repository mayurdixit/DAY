import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserService } from '../../user.service';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ResetPasswordComponent implements OnInit {

  model: any = {};
  loading = false;
  loginUrl = 'login';
  currentUser: any = {};

  constructor(private user: UserService,
  private router: Router,
  private alertService: AlertService) { 
    let stringifyCurrentUser = localStorage.getItem("currentUser");
    this.currentUser = JSON.parse(stringifyCurrentUser);
    console.log("user.userNAme=" + this.currentUser.userName);
    this.model.username = this.currentUser.userName;
    console.log("model.username = " + this.model.username);
  }

  ngOnInit() {
  }

  resetPassword(ev) {
    console.log("old password = " + this.model.oldPassword);
    console.log("new password = " + this.model.newPassword);
    this.user.resetPassword(this.model.username, this.model.oldPassword, this.model.newPassword).subscribe(
      data=>{
        this.router.navigate([this.loginUrl]);
      },
      error => {
        console.log("got error");
        console.log(error);
        this.alertService.error("Unable to reset Password");
        this.loading = false;
      }
    );
  }
}
