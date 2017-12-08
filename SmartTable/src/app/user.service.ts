import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';


//return this.http.post('http://localhost:3000/adduser', JSON.stringify(data), options)
//https://github.com/sorakthunly/angular2-express-node-mysql-seed-project

@Injectable()
export class UserService {

  private isUserLoggedIn;
  public username;
  private password;
  private aclObj;

  //private authenticateUserURL = 'http://localhost:8888/authorize';
  private authenticateUserURL = "../internal/authorize";

  constructor(private http: HttpClient, private router:Router) { 
    this.isUserLoggedIn = false;
  }

  getKendraList(){
    return this.aclObj.kendraInfoList;
  }

  setUserLoggedIn(loggedIn: boolean) {
    this.isUserLoggedIn = loggedIn;
  }

  getUserLoggedIn() {
    return this.isUserLoggedIn;
  }

  setPassword(passwd: String) {
    this.password = passwd;
  }

  authenticateUser(userdata: String){
    var data={};
   
    console.log("In Authenticate User = " + userdata );
    console.log("authenticateUserURL=" + this.authenticateUserURL);
    
    

    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');
    
    let requestBody = { "userName": this.username , "password":  this.password };
    console.log("body=" + requestBody);

    this.http.post(this.authenticateUserURL, requestBody, {
      headers: headers,
    })
    .toPromise()
    .then(response => {
      console.log("response=" + response);
      this.aclObj = response;
      let kendraList = this.getKendraList();
      console.log("response lenght=" + kendraList.length);
      if(kendraList.length > 0) {
        this.setUserLoggedIn(true);        
        this.router.navigate(['dashboard']);
      } else {
        this.setUserLoggedIn(false);
      }
      
      
    }) 
    .catch(this.handleError);
}

private handleError(error: any): Promise<any> {
  console.error('Some error occured', error);
  return Promise.reject(error.message || error);
}
}
