import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ToyCenterComponent } from './playground/toy-center/toy-center.component';
import { ToyDetailsComponent } from './playground/toy-details/toy-details.component';


const routes: Routes = [
  { path: '', component: ToyCenterComponent },
  { path: 'playground', redirectTo: '', pathMatch: 'full' },
  { path: 'playground/toy-details/:id', component: ToyDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
