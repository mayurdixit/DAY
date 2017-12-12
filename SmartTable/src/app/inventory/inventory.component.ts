import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class InventoryComponent implements OnInit {
 

  constructor(private router: Router, private user: UserService) {
    console.log(this.user.isUserLoggedIn());    
   }

  ngOnInit() {   }

}
