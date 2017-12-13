import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Route } from '@angular/router';

@Injectable()
export class InventoryService {

  private inventoryDataURL = "../internal/kendra_inventory";
  constructor(private http: HttpClient) { }
  private inventoryInfo;
  public isInventoryDataAvailable=false;

  getInventoryInfo(){
    return this.inventoryInfo;
  }

  getInventaryDataForKendra(kendraId: number){
    console.log("Inventory Service: KendraId=" + kendraId);
    let url = this.inventoryDataURL + "/" + kendraId;
    this.http.get(url)
    .toPromise()
    .then(response=>{
      console.log("Response = " + JSON.stringify(response));
      this.inventoryInfo = response;
      if(this.inventoryInfo.length > 0){
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
