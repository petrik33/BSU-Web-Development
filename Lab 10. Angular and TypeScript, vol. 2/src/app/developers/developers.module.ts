import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeveloperListComponent } from './developer-list/developer-list.component';
import { DeveloperDetailsComponent } from './developer-details/developer-details.component';
import { DeveloperCenterComponent } from './developer-center/developer-center.component';
import { DevelopersRoutingModule } from './developers-routing.module';



@NgModule({
  imports: [
    CommonModule,
    DevelopersRoutingModule
  ],
  declarations: [
    DeveloperListComponent,
    DeveloperDetailsComponent,
    DeveloperCenterComponent
  ],
  exports: [
    DeveloperCenterComponent
  ]
})
export class DevelopersModule { }
