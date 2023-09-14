import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JumbotronComponent } from './jumbotron/jumbotron.component';



@NgModule({
  declarations: [
    JumbotronComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    JumbotronComponent
  ]
})
export class UtilsModule { }
