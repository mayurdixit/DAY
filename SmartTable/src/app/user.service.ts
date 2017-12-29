import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';


//return this.http.post('http://localhost:3000/adduser', JSON.stringify(data), options)
//https://github.com/sorakthunly/angular2-express-node-mysql-seed-project

@Injectable()
export class UserService {

  private aclObj;

  //private authenticateUserURL = 'http://localhost:8888/authorize';
  private authenticateUserURL = "../internal/authorize";
  private allUsersForAppZoneURL = "../internal/user-by-app";

  constructor(private http: HttpClient, private router: Router) {
    let stringifyAclObj = localStorage.getItem('currentUser');
    this.aclObj = JSON.parse(stringifyAclObj);
    console.log("User Service Constructor: " + this.aclObj);
  }


  ngOnInit() {   }

  getZoneName() {
    return this.aclObj.zoneInfo.name;
  }

  getKendraList() {
    console.log(JSON.stringify(this.aclObj.kendraInfoList));
    return this.aclObj.kendraInfoList;
  }

  getZoneList() {
    console.log(JSON.stringify(this.aclObj.kendraInfoList));
    return this.aclObj.zoneInfoList;
  }

  getApplicationList() {
    console.log(JSON.stringify(this.aclObj.applicationList));
    return this.aclObj.applicationList;
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

    return this.http.post(this.allUsersForAppZoneURL, requestBody, {
      headers: headers,
    })
      .map(response => {
        console.log("response=" + response);       
        return response;
      })    
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

    return this.http.post(this.authenticateUserURL, requestBody, {
      headers: headers,
    })
      .map(response => {
        console.log("response=" + response);
        this.aclObj = response;
        //let kendraList = this.getKendraList();
        //console.log("response lenght=" + kendraList.length);
        localStorage.setItem('currentUser', JSON.stringify(this.aclObj));
        //if (kendraList.length > 0) {
        //  this.router.navigate(['inventory']);

        //}
        return response;
      })     
  }

  logout() {
    console.log("Loging out");
    localStorage.removeItem('currentUser');
    localStorage.clear();
    sessionStorage.clear();
  }

}
