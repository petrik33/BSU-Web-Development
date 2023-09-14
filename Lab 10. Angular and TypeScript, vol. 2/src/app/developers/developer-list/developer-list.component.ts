import { Component } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-developer-list',
  templateUrl: './developer-list.component.html',
  styleUrls: ['./developer-list.component.css']
})
export class DeveloperListComponent {
  developers : Developer[] = [];
  
  constructor(private service: DevelopersService) {
    
  }

  ngOnInit() {
    this.developers = this.service.getDevelopers()
  }
}
