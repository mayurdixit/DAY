import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class RouteService {

  constructor(private http: HttpClient) { }

  public doPost(url: string, requestBody: any, headers: HttpHeaders){       
    headers.append('auth-token', this.getAuthToken());
    return this.http.post(url, requestBody, {
      headers: headers,
    })
      .map((response: Response) => {
        //console.log("response=" + JSON.stringify(response));                             
        return response;
      })     
  }  

  public doGet(url: string, headers: HttpHeaders){
    console.log("Doing Get");
    let header = {'auth-token': this.getAuthToken()};
    //headers.append('auth-token', `Bearer ${authToken}`);
     return this.http.get(url, {
       headers: header,
     })    
      .map((response: Response) => {
        //console.log("response=" + JSON.stringify(response));                     
        return response;
      })     
  } 
  
  private getAuthToken(){
    var authToken = "";
    var currentUser:any = localStorage.getItem('currentUser');
    var JSONCurrentUser:any;
    console.log('currentUser:' + currentUser);
    if(currentUser){
      JSONCurrentUser = JSON.parse(currentUser);
      authToken = JSONCurrentUser.authToken;
      console.log('authToken = ' + authToken);
    } 
    return authToken;  
  }
}
