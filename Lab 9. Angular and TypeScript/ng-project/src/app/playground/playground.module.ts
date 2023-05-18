import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToyCenterComponent } from './toy-center/toy-center.component';
import { ToyListComponent } from './toy-list/toy-list.component';
import { ToyDetailsComponent } from './toy-details/toy-details.component';



@NgModule({
  declarations: [
    ToyCenterComponent,
    ToyListComponent,
    ToyDetailsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PlaygroundModule { }
