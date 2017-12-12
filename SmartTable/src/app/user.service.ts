import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';


//return this.http.post('http://localhost:3000/adduser', JSON.stringify(data), options)
//https://github.com/sorakthunly/angular2-express-node-mysql-seed-project

@Injectable()
export class UserService {

  private aclObj;

  //private authenticateUserURL = 'http://localhost:8888/authorize';
  private authenticateUserURL = "../internal/authorize";

  constructor(private http: HttpClient, private router:Router) {     
    let stringifyAclObj = sessionStorage.getItem('currentUser');   
    this.aclObj = JSON.parse(stringifyAclObj); 
    console.log("User Service Constructor: " + this.aclObj);
  }

  getKendraList(){
    return this.aclObj.kendraInfoList;
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('currentUser') != null;
  }

  authenticateUser(userdata: String, psword: String){
    var data={};
   
    console.log("In Authenticate User = " + userdata + " / " + psword );
    console.log("authenticateUserURL=" + this.authenticateUserURL);
    
    

    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');
    
    let requestBody = { "userName": userdata , "password":  psword };
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
      sessionStorage.setItem('currentUser', JSON.stringify(this.aclObj));
      if(kendraList.length > 0) {       
        this.router.navigate(['inventory']);
        
      }         
    }) 
    .catch(this.handleError);
}

logout(){
  sessionStorage.removeItem('currentUser');
}

private handleError(error: any): Promise<any> {
  console.error('Some error occured', error);
  return Promise.reject(error.message || error);
}
}
