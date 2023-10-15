import { Component, inject } from '@angular/core';
import { FirebaseApp } from '@angular/fire/app';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'base-project';
  constructor(private app : FirebaseApp) {
    app = inject(FirebaseApp);
  }
}
