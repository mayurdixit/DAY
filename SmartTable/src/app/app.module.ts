import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { InventoryComponent } from './inventory/inventory.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { HeaderComponent } from './header/header.component';

import { InventoryService } from './inventory.service';
import { UserService } from './user.service';
import { AuthguardGuard } from './authguard.guard';
import { HttpClientModule } from '@angular/common/http';

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
    HeaderComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [UserService, AuthguardGuard, InventoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
