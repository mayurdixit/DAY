import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable()
export class AlertService {
  private subject = new Subject<any>();
  private keepAfterNavigationChange = false;

  constructor(private router: Router) {
    router.events.subscribe(event => {
      if(event instanceof NavigationStart){
        this.keepAfterNavigationChange = false;
      } else {
        this.subject.next();
      }
    });
  }

  success(message: string, keepAfterNavigationChange = false){
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next({type: 'success', text: message});
  }

  error(message: string, keepAfterNavigationChange = false){    
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next({type: 'error', text: message});
  }

  getMessage(): Observable<any>{
    console.log(this.subject);
    return this.subject.asObservable();
  }

  clearMessage(){
    this.subject.next();
  }
}
