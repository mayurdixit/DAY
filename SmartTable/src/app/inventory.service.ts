import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Route } from '@angular/router';
import { forEach } from '@angular/router/src/utils/collection';
import { Text } from '@angular/compiler';
import { EquipmentInfo } from './inventory/equipmentInfo';

@Injectable()
export class InventoryService {

  private inventoryDataURL = "../internal/kendra_inventory";
  private addEquipmentURL = "../internal/add_inventory";
  private getEquipmentURL = "../internal/inventory";

  private inventoryInfo;
  public isInventoryDataAvailable = false;
  private selectedZoneId;
  private selectedKendraId;
  private selectedZoneName;
  private selectedKendraName;
  private equipmentId;

  constructor(private http: HttpClient) { 
    
   }

  getInventoryInfo() {
    return this.inventoryInfo;
  }

  getSelectedZoneId() {
    return this.selectedZoneId;
  }

  setSelectedZoneId(zoneId: number) {
    this.selectedZoneId = zoneId;
  }

  getSelectedKendraId() {
    return this.selectedKendraId;
  }

  setSelectedKendraId(kendraId: number) {
    this.selectedKendraId = kendraId;
  }

  getSelectedZoneName() {
    return this.selectedZoneName;
  }

  setSelectedZoneName(zoneName: string) {
    this.selectedZoneName = zoneName;
  }

  getSelectedKendraName() {
    return this.selectedKendraName;
  }

  setSelectedKendraName(kendraName: string) {
    this.selectedKendraName = kendraName;
  }

  getEquipmentId(){
    return this.equipmentId;
  }
  setEquipmentId(id: number){
    this.equipmentId = id;
  }

  getInventaryDataForKendra(kendraId: number) {
    console.log("Inventory Service: KendraId=" + kendraId);
    let url = this.inventoryDataURL + "/" + kendraId;
    this.http.get(url)
      .toPromise()
      .then(response => {
        console.log("Response = " + JSON.stringify(response));
        this.inventoryInfo = response;
        if (this.inventoryInfo.length > 0) {
          this.isInventoryDataAvailable = true;
        } else {
          this.isInventoryDataAvailable = false;
        }
      })
      .catch(this.handleError);
  }

  getEquipmentInfo(equipmentId: number){
    let url = this.getEquipmentURL + "/" + equipmentId;
    console.log(("url=" + url));
    return this.http.get(url)
    .map(response => {
      console.log("Response = " + JSON.stringify(response));     
      return response; 
    })
  }

  saveEquipment(equipmentInfo: EquipmentInfo){
    console.log("InventoryService: saveEquipment");

    let header = new HttpHeaders();
    header.set('Access-Control-Allow-Origin', this.addEquipmentURL);
    header.append('Access-Control-Allow-Credentials', 'true');
    header.append('Content-type', 'application/json');
    let requestBody = equipmentInfo;
    return this.http.post(this.addEquipmentURL, requestBody, {
      headers: header,
    })
    .map(response => {
      console.log("response=" + response);      
      return response;
    })    
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }

}
