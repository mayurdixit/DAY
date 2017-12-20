import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { InventoryService } from '../../inventory.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-edit-equipment',
  templateUrl: './add-edit-equipment.component.html',
  styleUrls: ['./add-edit-equipment.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AddEditEquipmentComponent implements OnInit {

  private selectedZoneName;
  private selectedKendraName;
  
  constructor(private router: Router, private inventoryService: InventoryService) { 
    console.log("selectedZone = " + inventoryService.getSelectedZoneName());
    console.log("selectedKendra = " + inventoryService.getSelectedKendraName());
    this.selectedKendraName = inventoryService.getSelectedKendraName();
    this.selectedZoneName = inventoryService.getSelectedZoneName();
  }

  ngOnInit() {
  }

  cancelEqipment(){
    this.router.navigate(['/inventory']);
  }

}
