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


  private selectedKendraId;
  private selectedKendraName;
  private selectedZoneName;
  private selectedZoneId;
  private showInventoryData = false;
  private currentUser = null;

  constructor(private router: Router, private user: UserService, private inventoryService: InventoryService) {
    console.log("In Inventory: " + this.user.isUserLoggedIn());
    var stringifyCurrentUser = localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(stringifyCurrentUser);
    this.selectedKendraId = inventoryService.getSelectedKendraId();
    this.selectedZoneId = inventoryService.getSelectedZoneId();
    this.selectedKendraName = inventoryService.getSelectedKendraName();
    this.selectedZoneName = inventoryService.getSelectedZoneName();
    this.getInventory();
  }

  getInventory() {
    if (this.selectedKendraId != null) {
      console.log("Kendra Id = " + this.selectedKendraId);
      this.inventoryService.getInventaryDataForKendra(this.selectedKendraId);
      console.log("isInventoryDataAvailable=" + this.inventoryService.isInventoryDataAvailable)
    }
  }

  addEquipment() {
    console.log("Add equipment for " + this.selectedZoneId + " " + this.selectedZoneName);
    this.inventoryService.setEquipmentId(null);
    this.router.navigate(['inventory/add-equipment']);   
  }
  updateEquipment(equipmentId: number){
    console.log("Update equipment for equipment: " + equipmentId + " Zone" + this.selectedZoneId + "/" + this.selectedZoneName);
    this.inventoryService.setEquipmentId(equipmentId);
    this.router.navigate(['inventory/add-equipment']);
  }

  zoneChange(event) {
    this.selectedZoneName = event.target.options[event.target.selectedIndex].text;
    this.inventoryService.setSelectedZoneId(this.selectedZoneId);
    this.inventoryService.setSelectedZoneName(this.selectedZoneName);
    console.log("ZoneName=" + this.selectedZoneName);
  }

  kendraChange(event) {
    this.selectedKendraName = event.target.options[event.target.selectedIndex].text;
    this.inventoryService.setSelectedKendraId(this.selectedKendraId);
    this.inventoryService.setSelectedKendraName(this.selectedKendraName);
    console.log("KendraName=" + this.selectedKendraName);
  }

  ngOnInit() { }

}
