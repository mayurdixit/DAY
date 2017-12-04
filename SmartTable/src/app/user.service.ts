import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders } from '@angular/common/http';


//return this.http.post('http://localhost:3000/adduser', JSON.stringify(data), options)
//https://github.com/sorakthunly/angular2-express-node-mysql-seed-project

@Injectable()
export class UserService {

  private isUserLoggedIn;
  public username;

  //private authenticateUserURL = 'http://localhost:8888/authorize';
  private authenticateUserURL = "../authorize";

  constructor(private http: HttpClient) { 
    this.isUserLoggedIn = false;
  }

  setUserLoggedIn() {
    this.isUserLoggedIn = true;
  }

  getUserLoggedIn() {
    return this.isUserLoggedIn;
  }

  authenticateUser(userdata: String){
    var data={};
   
        console.log("In Authenticate User = " + userdata );
    let headers = new HttpHeaders();
    console.log("authenticateUserURL=" + this.authenticateUserURL);
    headers.set('Access-Control-Allow-Origin', this.authenticateUserURL);
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Content-type', 'application/json');
    this.http.post(this.authenticateUserURL, this.username, {
      headers: headers,
    })
    .toPromise()
    .then(response => console.log(response)) 
    .catch(this.handleError);
}

private handleError(error: any): Promise<any> {
  console.error('Some error occured', error);
  return Promise.reject(error.message || error);
}
}
