import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class InventoryComponent implements OnInit {
 

  private selectedKendra;
  private selectedZone;
  private showInventoryData = false;
  private currentUser = null;

  constructor(private router: Router, private user: UserService, private inventoryService: InventoryService) {
    //constructor(private router: Router, private user: UserService) {
    console.log("In Inventory: " + this.user.isUserLoggedIn());   
    var stringifyCurrentUser =  localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(stringifyCurrentUser);
    console.log(this.currentUser);
   }

   getInventory(){
     console.log("getting Inventory: " + this.selectedKendra);
     this.inventoryService.getInventaryDataForKendra(this.selectedKendra);     
     console.log("isInventoryDataAvailable=" + this.inventoryService.isInventoryDataAvailable)
   }

  ngOnInit() {   }

}
