import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit {

  name = '';

  constructor(private router: Router, private user: UserService) {
    console.log(this.user.getUserLoggedIn());
    console.log('username =' + this.user.username);
      this.name = this.user.username;
   }

  ngOnInit() {   }

}
