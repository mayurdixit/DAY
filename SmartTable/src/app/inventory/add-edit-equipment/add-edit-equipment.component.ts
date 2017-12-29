import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { InventoryService } from '../../inventory.service';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { EquipmentInfo } from '../../models/equipment-info';
import { InventoryOwnedBy } from '../../models/inventory-owned-by';

@Component({
  selector: 'app-add-edit-equipment',
  templateUrl: './add-edit-equipment.component.html',
  styleUrls: ['./add-edit-equipment.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddEditEquipmentComponent implements OnInit {

  private selectedZoneName;
  private selectedKendraName;
  private user;
  private equipmentInfo: EquipmentInfo = new EquipmentInfo();
  private ownedByList: InventoryOwnedBy = new InventoryOwnedBy();
  private equipmentId: number;
  private selectedEquipmentType;
  private selectedOwnedBy;

  model: any = {};

  constructor(private router: Router, private inventoryService: InventoryService, private alertService: AlertService) {
    var userStringify = localStorage.getItem('currentUser');
    this.user = JSON.parse(userStringify);
    console.log("selectedZone = " + inventoryService.getSelectedZoneName());
    console.log("selectedKendra = " + inventoryService.getSelectedKendraName());
    this.selectedKendraName = inventoryService.getSelectedKendraName();
    this.selectedZoneName = inventoryService.getSelectedZoneName();
    this.equipmentId = inventoryService.getEquipmentId();
    if(this.equipmentId != null){
      this.getEquipmentInfo();
    }
    console.log(inventoryService.getEquipmentTypes());
  }

  ngOnInit() {
  }

  cancelEqipment() {
    this.router.navigate(['/inventory']);
  }

  getEquipmentInfo(){
    console.log("getEquipmentInfo called");
    this.inventoryService.getEquipmentInfo(this.equipmentId).subscribe(
      data=>{        
        console.log(data);
        this.updateModel(data)
      },
      error=>{
        console.log("got error");
        this.alertService.error(error);
      }
    );
    console.log("EquipmentData to be updated=");
    //console.log(equipmentData);
  }

  getFormatedPurchaseDate(inputDate: Date){
    let tempDate: Date = new Date(inputDate);
    let year = tempDate.getFullYear();
    let day = tempDate.getDate();
    let month = tempDate.getMonth();
    month = month+1;
    let returnDate = year + "-" + month + "-" + day;
    console.log("returnDate = " + returnDate);
    return returnDate;
  }

  updateModel(data: any){    
    this.model.equipmentName = data.name;
    this.selectedEquipmentType = data.name;
    this.model.serialNumber = data.serialModelNumber;
    this.model.inUse = data.inUse;        
    this.model.purchasedOn = this.getFormatedPurchaseDate(data.purchasedOn);
    this.selectedOwnedBy = data.ownedBy;
    this.model.userSince = data.usedSince;
    this.model.storedAt = data.storedAt;
    this.model.comments = data.comment;
    this.model.contact1 = data.contact1_name;
    this.model.contact1Phone = data.contact1_phone;
    this.model.contact1Email = data.contact1_email;
    this.model.contact2 = data.contact2_name;
    this.model.contact2Phone = data.contact2_phone;
    this.model.contact2Email = data.contact2_email;
    console.log("data.createdOn:" + data.createdOn);
    this.model.createdOn = data.createdOn;
    this.model.id = data.id;
  }

  addEquipment() {
    console.log('add equipment');
    console.log(this.model.equipmentName);
    var newpurchasedOn: Date = new Date(this.model.purchasedOn);
    newpurchasedOn.setDate(newpurchasedOn.getDate() + 1);
    console.log("Purchased on ************ = " + newpurchasedOn);
    this.equipmentInfo.setName(this.selectedEquipmentType);
    this.equipmentInfo.setSerialModelNumber(this.model.serialNumber);
    this.equipmentInfo.setInUse(this.model.inUse);
    this.equipmentInfo.setPurchasedOn(newpurchasedOn);
    this.equipmentInfo.setOwnedBy(this.selectedOwnedBy);
    this.equipmentInfo.setUsedSince(this.model.userSince);
    this.equipmentInfo.setStoredAt(this.model.storedAt);
    this.equipmentInfo.setKendraId(this.inventoryService.getSelectedKendraId());
    this.equipmentInfo.setComment(this.model.comments);
    this.equipmentInfo.setUpdatedBy(this.user.userId);
    this.equipmentInfo.setContact1_name(this.model.contact1);
    this.equipmentInfo.setContact1_phone(this.model.contact1Phone);
    this.equipmentInfo.setContact1_email(this.model.contact1Email);
    this.equipmentInfo.setContact2_name(this.model.contact2);
    this.equipmentInfo.setContact2_phone(this.model.contact2Phone);
    this.equipmentInfo.setContact2_email(this.model.contact2Email);
    this.equipmentInfo.setCreatedOn(this.model.createdOn);
    this.equipmentInfo.setId(this.model.id);
    
    console.log(this.equipmentInfo);
    this.inventoryService.saveEquipment(this.equipmentInfo).subscribe(
      data => {
        console.log("success");
        this.router.navigate(['/inventory']);
      },
      error => {
        console.log("got error");
        this.alertService.error(error);       
      }
    );
}
}
