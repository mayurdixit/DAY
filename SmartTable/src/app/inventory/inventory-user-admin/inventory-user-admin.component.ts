import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user.service';
import { InventoryService } from '../../inventory.service';
import { AlertService } from '../../services/alert.service';
import { UrlSerializer } from '@angular/router/src/url_tree';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inventory-user-admin',
  templateUrl: './inventory-user-admin.component.html',
  styleUrls: ['./inventory-user-admin.component.css']
})
export class InventoryUserAdminComponent implements OnInit {

  private appId;
  private userRoleList;
  constructor(private userService: UserService,
    private inventoryService: InventoryService,
    private alertService: AlertService,
    private router: Router) { 
    this.appId = localStorage.getItem("selectedApp");
    console.log("appId=" + this.appId);
    userService.getAllUsersForAppZone(this.appId, inventoryService.getSelectedZoneId()).subscribe(
      data =>{
        console.log(data);
        this.userRoleList = data;
      },
      error=>{
        this.alertService.error(error);
      }
    )
    console.log(this.userRoleList);
  }

  ngOnInit() {
  }

  backToInventory(){
    this.router.navigate(['/inventory']);
  }

}
