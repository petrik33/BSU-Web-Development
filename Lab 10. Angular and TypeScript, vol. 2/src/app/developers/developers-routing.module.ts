import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeveloperListComponent } from './developer-list/developer-list.component';
import { DeveloperDetailsComponent } from './developer-details/developer-details.component';
import { DeveloperCenterComponent } from './developer-center/developer-center.component';
import { DeveloperFormComponent } from './developer-form/developer-form.component';

const routes: Routes = [
  {
    path: 'center', title: 'Developer Center', component: DeveloperCenterComponent, children: [
      { path: 'list', component: DeveloperListComponent },
      { path: 'developer/:id', component: DeveloperDetailsComponent },
      { path: 'form/:id', component: DeveloperFormComponent },
      { path: 'form/new', component: DeveloperFormComponent },
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      { path: '**', redirectTo: 'list', pathMatch: 'full' }
    ]
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevelopersRoutingModule { }
