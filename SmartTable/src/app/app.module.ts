import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { InventoryComponent } from './inventory/inventory.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { HeaderComponent } from './header/header.component';

import { InventoryService } from './inventory.service';
import { UserService } from './user.service';
import { AlertService} from './services/alert.service';
import { AuthguardGuard } from './authguard.guard';
import { HttpClientModule } from '@angular/common/http';
import { AlertComponent } from './alert/alert.component';
import { AddEditEquipmentComponent } from './inventory/add-edit-equipment/add-edit-equipment.component';
import { InventoryUserAdminComponent } from './inventory/inventory-user-admin/inventory-user-admin.component';

const appRoutes: Routes =[
  {
    path: '',
    component: LoginFormComponent
  },
  {
    path: 'inventory',
    canActivate: [AuthguardGuard],
    component: InventoryComponent
  },
  {
    path: 'inventory/add-equipment',
    canActivate: [AuthguardGuard],
    component: AddEditEquipmentComponent
  },
  {
    path: 'inventory/users-admin',
    canActivate: [AuthguardGuard],
    component: InventoryUserAdminComponent
  },
  {
    path: 'home',
    canActivate: [AuthguardGuard],
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    InventoryComponent,
    HomeComponent,
    LoginFormComponent,
    HeaderComponent,
    AlertComponent,
    AddEditEquipmentComponent,
    InventoryUserAdminComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [UserService, AuthguardGuard, InventoryService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
