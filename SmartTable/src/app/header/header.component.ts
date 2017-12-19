import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {UserService} from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {

  private currentUser;
  isUserLoggedIn=false;
  constructor(private user: UserService) { 
    console.log("in header isUserLoggedIn=" + user.isUserLoggedIn());
    var stringifyUser = localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(stringifyUser);

    console.log(this.currentUser.applicationList);
  }
  ngOnInit() {}
}
