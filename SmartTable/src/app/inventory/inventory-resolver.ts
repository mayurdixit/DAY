import { Injectable } from "@angular/core";
import { Resolve } from "@angular/router";
import { Observable } from "rxjs/Observable";
import { InventoryService } from "../inventory.service";



@Injectable()
export class InventoryResolver implements Resolve<string> {
    constructor(private inventoryService: InventoryService) {}

    resolve(){
        this.inventoryService.initAllEquipmentTypes();        
        return 'working';
    }
}
