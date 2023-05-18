import { Injectable } from '@angular/core';
import { Toy, Toys } from 'src/data/mock-toy-list';

@Injectable({
  providedIn: 'root'
})
export class PlaygroundService {

  getToys(): Toy[] {
    return Toys;
  }

  getToyData(id: number) {
    return Toys.find(toy => toy.id === id);
  }

  constructor() { }
}
