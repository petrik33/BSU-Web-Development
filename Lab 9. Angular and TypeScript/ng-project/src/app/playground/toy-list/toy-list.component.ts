import { Component } from '@angular/core';
import { Toys, Toy } from 'src/data/mock-toy-list';
import { Router } from '@angular/router';

@Component({
  selector: 'app-toy-list',
  templateUrl: './toy-list.component.html',
  styleUrls: ['./toy-list.component.css']
})
export class ToyListComponent {
  toys = Toys.slice();
  constructor(private router: Router) { }

  showToyDetails(toy: Toy): void {
    this.router.navigate(['/playground/toy-details', toy.id]);
  }
}
