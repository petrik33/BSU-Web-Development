import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Toy, Toys } from 'src/data/mock-toy-list';

@Component({
  selector: 'app-toy-details',
  templateUrl: './toy-details.component.html',
  styleUrls: ['./toy-details.component.css']
})
export class ToyDetailsComponent implements OnInit {
  toy: Toy | null = null;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const toyId = this.route.snapshot.paramMap.get('id');
    // Fetch the toy data based on the toyId
    // Replace this with your actual code to fetch the toy data

    // Example code to fetch toy data from mock-toy-list
    const toyData = Toys.find((toy) => toy.id === Number(toyId))
    if (toyData) {
      this.toy = toyData;
    }
  }
}
