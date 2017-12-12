import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Route } from '@angular/router';

@Injectable()
export class InventoryService {

  constructor(private http: HttpClient, private router:Route) { }

  getInventaryDataForKendra(kendraId: number){
    
  }

}
