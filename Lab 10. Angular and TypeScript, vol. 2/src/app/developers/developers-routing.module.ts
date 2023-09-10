import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeveloperListComponent } from './developer-list/developer-list.component';
import { DeveloperDetailsComponent } from './developer-details/developer-details.component';

const routes: Routes = [
  { path: 'list', component: DeveloperListComponent },
  { path: 'developer/:id', component: DeveloperDetailsComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { bindToComponentInputs: true })],
  exports: [RouterModule]
})
export class DevelopersRoutingModule { }
