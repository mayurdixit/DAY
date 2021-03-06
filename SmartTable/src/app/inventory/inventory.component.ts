import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { InventoryService } from '../inventory.service';
import { AlertService } from '../services/alert.service';
import { RoleMapping } from '../models/role-mapping';
import { InventoryOwnedBy } from '../models/inventory-owned-by';

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
  private roleMapping: RoleMapping = new RoleMapping();
  private ownedBy: InventoryOwnedBy = new InventoryOwnedBy();
  private resolverData: any;
  private inventoryTable = [];
  private sortType="";
  private sortReverse="";
  private searchFish=false;

  constructor(private router: Router,
    private user: UserService,
    private inventoryService: InventoryService,
    private alertService: AlertService,
    private activatedRoute: ActivatedRoute) {

    this.resolverData = this.activatedRoute.snapshot.data;   
    console.log("In Inventory: " + this.user.isUserLoggedIn());    
    var stringifyCurrentUser = localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(stringifyCurrentUser);
    if(this.currentUser.accessRole.id === this.roleMapping.KENDRA_ROLE){
      this.setSelectedKendraForUser();
    }
    this.selectedKendraId = inventoryService.getSelectedKendraId();
    this.selectedZoneId = inventoryService.getSelectedZoneId();
    this.selectedKendraName = inventoryService.getSelectedKendraName();
    this.selectedZoneName = inventoryService.getSelectedZoneName();
    this.getInventory();
  }

  getInventory() {
    if (this.selectedKendraId != null) {
      console.log("Kendra Id = " + this.selectedKendraId);
      this.inventoryService.getInventaryDataForKendra(this.selectedKendraId).subscribe(
        data=>{
          console.log("inventoryInfo = " + JSON.stringify(data));
          this.inventoryService.setInventoryInfo(data);
          this.inventoryService.isInventoryDataAvailable = true;
          this.createInventoryTable();
        },
        error=>{
          console.log(error.status);
          console.log(error);
          this.alertService.error(error, true, error.status);
        }
      );  
      console.log("isInventoryDataAvailable=" + this.inventoryService.isInventoryDataAvailable)
    }
  }

  createInventoryTable(){    
    this.inventoryTable = [];
    var equipmentType= "";
    this.sortType = "equipment_type";
    this.sortReverse = false;

    for(let equipmentInfo of this.inventoryService.getInventoryInfo()){
      equipmentType = this.getEquipmentTypeName(equipmentInfo.inventory.name);
      console.log("equipmentType= " + equipmentType);
      var inventoryRecord = {
      "equipment_type" : equipmentType,
      "serialNumber" : equipmentInfo.inventory.serialModelNumber,
      "inUse" : equipmentInfo.inventory.inUse,
      "purchasedOn" : equipmentInfo.inventory.purchasedOn,
      "ownedBy" : equipmentInfo.inventory.ownedBy,
      "usedSince" : equipmentInfo.inventory.usedSince,
      "storedAt" : equipmentInfo.inventory.storedAt,
      "name" : equipmentInfo.kendraInfo.name,
      "comment" : equipmentInfo.inventory.comment,
      "contact1_name" : equipmentInfo.inventory.contact1_name,
      "contact1_phone" : equipmentInfo.inventory.contact1_phone,
      "contact1_email" : equipmentInfo.inventory.contact1_email,
      "contact2_name" : equipmentInfo.inventory.contact2_name,
      "contact2_phone" : equipmentInfo.inventory.contact2_phone,
      "contact2_email" : equipmentInfo.inventory.contact2_email,
      "lastUpdatedOn" : equipmentInfo.inventory.lastUpdatedOn
      }
      this.inventoryTable.push(inventoryRecord);
    }
    console.log("table = " + this.inventoryTable);
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
  getUsersInfo(){    
    this.router.navigate(['inventory/users-admin']);
  }
  deleteEquipment(equipmentId: number){
    console.log("Delete equipment for equipment: " + equipmentId + " Zone" + this.selectedZoneId + "/" + this.selectedZoneName);
    var confirmData = "are you sure, you want to remove equipment?";    
    if(confirm(confirmData)){ 
      console.log("Yes delete");
      this.inventoryService.setEquipmentId(equipmentId);
      this.inventoryService.deleteEquipment(equipmentId).subscribe(
        data=>{        
          this.alertService.success("Inventory Deleted Successfully", true);
          this.getInventory();
        },
        error=>{
          console.log(error);
          this.alertService.error(error);
        }
      )
    } else {
      console.log("No");
    }    
  }

  setSelectedKendraForUser(){
    this.inventoryService.setSelectedKendraId(this.currentUser.kendraInfoList[0].id);
    this.inventoryService.setSelectedKendraName(this.currentUser.kendraInfoList[0].name);
    this.inventoryService.setSelectedZoneId(this.currentUser.zoneInfoList[0].id);
    this.inventoryService.setSelectedZoneName(this.currentUser.zoneInfoList[0].name);
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

  getKendraInfoList(){
    let kendraInfoList = this.currentUser.kendraInfoList;
    if(this.selectedZoneId == null){
      return kendraInfoList;
    } else {
      var newKendraInfoList: any[] = new Array();
      for(let kendraInfo of kendraInfoList){  
        //console.log("selected zoneId = " + this.selectedZoneId);      
        //console.log(kendraInfo);
        if(kendraInfo.parent == this.selectedZoneId){
          newKendraInfoList.push(kendraInfo);
        }
      }
      return newKendraInfoList;
    }
  }

  getEquipmentTypeName(id: number){
    var equipmentTypeName="";    
      for(let equipmentType of this.inventoryService.getEquipmentTypes()){
        if(id === equipmentType.id){
          equipmentTypeName = equipmentType.name;
        }
      }
    return equipmentTypeName;
  }
  ngOnInit() { }

}
