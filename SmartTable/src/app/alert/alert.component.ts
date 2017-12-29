import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';
import { Subscription } from 'rxjs/Subscription';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AlertComponent implements OnDestroy {
  private subscription: Subscription;
  message: any;

  constructor(private alertService: AlertService ) {
    this.subscription = alertService.getMessage().subscribe(message => {      
      this.message = message;
    });    
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  clearMessage(){
    this.alertService.clearMessage();
  }

}
