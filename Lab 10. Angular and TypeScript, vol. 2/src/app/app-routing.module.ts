import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeveloperCenterComponent } from './developers/developer-center/developer-center.component';

const routes: Routes = [
  { path: '', redirectTo: 'center', pathMatch: 'full' },
  { path: 'center', component: DeveloperCenterComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }