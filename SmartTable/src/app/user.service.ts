import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouteService } from './services/route.service';


//return this.http.post('http://localhost:3000/adduser', JSON.stringify(data), options)
//https://github.com/sorakthunly/angular2-express-node-mysql-seed-project

@Injectable()
export class UserService {

  //private aclObj;
  private currentUser;

  //private authenticateUserURL = 'http://localhost:8888/authorize';
  private authenticateUserURL = "../internal/authorize";
  private resetPasswordURL = "../internal/change-password";
  private allUsersForAppZoneURL = "../internal/user-by-app";

  constructor(private http: HttpClient, private router: Router, private myRouter: RouteService) {
    let stringifyCurrentUser = localStorage.getItem('currentUser');
    this.currentUser = JSON.parse(stringifyCurrentUser);
    console.log("User Service Constructor: " + this.currentUser);
  }


  ngOnInit() {   }

  getZoneName() {
    return this.currentUser.zoneInfo.name;
  }

  getKendraList() {
    console.log(JSON.stringify(this.currentUser.kendraInfoList));
    return this.currentUser.kendraInfoList;
  }

  getZoneList() {
    console.log(JSON.stringify(this.currentUser.kendraInfoList));
    return this.currentUser.zoneInfoList;
  }

  getApplicationList() {
    console.log(JSON.stringify(this.currentUser.applicationList));
    return this.currentUser.applicationList;
  }

  isUserLoggedIn() {
    return localStorage.getItem('currentUser') != null;
  }

  getAllUsersForAppZone(appId: number, zoneId: number){
    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');

    let requestBody = { "appId": appId, "zoneId": zoneId };
    console.log("body=" + requestBody);

    return this.myRouter.doPost(this.allUsersForAppZoneURL, requestBody, headers);
    // return this.http.post(this.allUsersForAppZoneURL, requestBody, {
    //   headers: headers,
    // })
    //   .map(response => {
    //     console.log("response=" + response);       
    //     return response;
    //   })    
  }

  resetPassword(userName: String, oldPsword: String, newPsword: String){
    var data = {};
    
    
    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');

    let requestBody = { "userName": userName, "oldPassword": oldPsword, "newPassword": newPsword };
    console.log("body=" + requestBody);

    return this.myRouter.doPost(this.resetPasswordURL, requestBody,headers);
    // return this.http.post(this.resetPasswordURL, requestBody, {
    //   headers: headers,
    // })
    //   .map(response => {
    //     console.log("response=" + response);                
    //     return response;
    //   })     
  }

  authenticateUser(userdata: String, psword: String) {
    var data = {};

    console.log("In Authenticate User = " + userdata + " / " + psword);
    console.log("authenticateUserURL=" + this.authenticateUserURL);

    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');

    let requestBody = { "userName": userdata, "password": psword };
    console.log("body=" + requestBody);

    return this.myRouter.doPost(this.authenticateUserURL, requestBody, headers);

    // return this.http.post(this.authenticateUserURL, requestBody, {
    //   headers: headers,
    // })
    //   .map((response: Response) => {
    //     console.log("response=" + JSON.stringify(response));                     
    //     return response;
    //   })     
  }

  logout() {
    console.log("Loging out");
    localStorage.removeItem('currentUser');
    localStorage.clear();
    sessionStorage.clear();
  }

}
