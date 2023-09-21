import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { DeveloperListComponent } from './developer-list/developer-list.component';
import { DeveloperDetailsComponent } from './developer-details/developer-details.component';
import { DeveloperCenterComponent } from './developer-center/developer-center.component';
import { DevelopersRoutingModule } from './developers-routing.module';
import { DeveloperFormComponent } from './developer-form/developer-form.component';



@NgModule({
  imports: [
    CommonModule,
    DevelopersRoutingModule,
    FormsModule

  ],
  declarations: [
    DeveloperListComponent,
    DeveloperDetailsComponent,
    DeveloperCenterComponent,
    DeveloperFormComponent
  ],
  exports: [
    DeveloperCenterComponent
  ]
})
export class DevelopersModule { }
