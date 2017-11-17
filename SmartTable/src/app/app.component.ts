import { Component } from '@angular/core';

export class TableData {
  constructor(
    public country: string,
    public capital: string,
    public id: number) {}
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  visible = true;
  counter = 0;
  username = 'Mayur';

  constructor() {
    setTimeout(() => {
      this.counter = 10;
    }, 2000);
  }

  usernameChange(ev){
    console.log(ev.target.value);
  }
  
}
