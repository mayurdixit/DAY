import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { InventoryService } from '../../inventory.service';

@Component({
  selector: 'app-add-edit-equipment',
  templateUrl: './add-edit-equipment.component.html',
  styleUrls: ['./add-edit-equipment.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddEditEquipmentComponent implements OnInit {

  private selectedZoneName;
  private selectedKendraName;
  
  constructor(inventoryService: InventoryService) { 
    console.log("selectedZone = " + inventoryService.getSelectedZoneName());
    console.log("selectedKendra = " + inventoryService.getSelectedKendraName());
    this.selectedKendraName = inventoryService.getSelectedKendraName();
    this.selectedZoneName = inventoryService.getSelectedZoneName();
  }

  ngOnInit() {
  }

}
