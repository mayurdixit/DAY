import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { InventoryService } from '../../inventory.service';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { EquipmentInfo } from '../EquipmentInfo';

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
  private equipmentId: number;

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
  }

  ngOnInit() {
  }

  cancelEqipment() {
    this.router.navigate(['/inventory']);
  }

  getEquipmentInfo(){
    console.log("getEquipmentInfo called");
    var equipmentData = this.inventoryService.getEquipmentInfo(this.equipmentId);
    console.log(equipmentData);
  }

  addEquipment() {
    console.log('add equipment');
    console.log(this.model.equipmentName);
    this.equipmentInfo.setName(this.model.equipmentName);
    this.equipmentInfo.setSerialModelNumber(this.model.serialNumber);
    this.equipmentInfo.setInUse(this.model.inUse);
    this.equipmentInfo.setPurchasedOn(this.model.purchasedOn);
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
