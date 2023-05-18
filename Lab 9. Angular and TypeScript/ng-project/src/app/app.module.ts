import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomJumbotronComponent } from './custom-jumbotron/custom-jumbotron.component';
import { PlaygroundModule } from './playground/playground.module';

@NgModule({
  declarations: [
    AppComponent,
    CustomJumbotronComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PlaygroundModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
