import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeveloperCenterComponent } from './developers/developer-center/developer-center.component';

const routes: Routes = [
  { path: '', redirectTo: 'center', pathMatch: 'full' },
  { path: 'center', loadChildren: () => import('./developers/developers.module').then(m => m.DevelopersModule) },
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { bindToComponentInputs: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }