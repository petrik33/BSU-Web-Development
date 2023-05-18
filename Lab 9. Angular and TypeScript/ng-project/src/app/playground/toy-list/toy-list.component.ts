import { Component } from '@angular/core';
import { Toy } from 'src/data/mock-toy-list';
import { Router } from '@angular/router';
import { PlaygroundService } from '../playground.service';

@Component({
  selector: 'app-toy-list',
  templateUrl: './toy-list.component.html',
  styleUrls: ['./toy-list.component.css']
})
export class ToyListComponent {
  toys: Toy[] = [];
  constructor(private playgroundService: PlaygroundService, private router: Router) { }

  ngOnInit() {
    this.toys = this.playgroundService.getToys();
  }

  showToyDetails(toy: Toy): void {
    this.router.navigate(['/playground/toy-details', toy.id]);
  }
}
