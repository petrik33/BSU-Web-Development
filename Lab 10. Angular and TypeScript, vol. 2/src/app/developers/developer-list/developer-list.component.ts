import {Component} from '@angular/core';
import {DevelopersService, IDeveloper} from '../service/developers.service';

@Component({
  selector: 'app-developer-list',
  templateUrl: './developer-list.component.html',
  styleUrls: ['./developer-list.component.css']
})
export class DeveloperListComponent {
  developers: IDeveloper[] = []

  constructor(private service: DevelopersService) {
  }

  ngOnInit() {
    this.service.getDevelopers().subscribe(data => {
      this.developers = data;
    });
  }
}
