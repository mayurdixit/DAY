import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {UserService} from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {

  isUserLoggedIn=false;
  constructor(private user: UserService) { 
  }
  ngOnInit() {}
}
