import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Route } from '@angular/router';
import { forEach } from '@angular/router/src/utils/collection';
import { Text } from '@angular/compiler';

@Injectable()
export class InventoryService {

  private inventoryDataURL = "../internal/kendra_inventory";
  constructor(private http: HttpClient) { 
   
  }
  private inventoryInfo;
  public isInventoryDataAvailable = false;
  private selectedZoneId;
  private selectedKendraId;
  private selectedZoneName;
  private selectedKendraName;

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

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }

}
