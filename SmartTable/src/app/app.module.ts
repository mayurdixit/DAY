import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { HeaderComponent } from './header/header.component';

import { UserService } from './user.service';
import { AuthguardGuard } from './authguard.guard';

const appRoutes: Routes =[
  {
    path: '',
    component: LoginFormComponent
  },
  {
    path: 'dashboard',
    canActivate: [AuthguardGuard],
    component: DashboardComponent
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
    DashboardComponent,
    HomeComponent,
    LoginFormComponent,
    HeaderComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
  ],
  providers: [UserService, AuthguardGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
