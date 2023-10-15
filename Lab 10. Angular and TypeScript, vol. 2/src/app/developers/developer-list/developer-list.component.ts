import { Component, OnInit } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';
import { Observable, from, of } from 'rxjs';

@Component({
  selector: 'app-developer-list',
  templateUrl: './developer-list.component.html',
  styleUrls: ['./developer-list.component.css']
})
export class DeveloperListComponent implements OnInit {
  developers$: Observable<Developer[]> = of([]);

  constructor(private service: DevelopersService) { }

  ngOnInit() {
    this.developers$ = from(this.service.getDevelopers());
  }
}
