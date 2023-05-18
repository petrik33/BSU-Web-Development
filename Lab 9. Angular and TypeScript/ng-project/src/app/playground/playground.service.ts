import { Injectable } from '@angular/core';
import { Toy, Toys } from 'src/data/mock-toy-list';

@Injectable({
  providedIn: 'root'
})
export class PlaygroundService {

  getToys(): Toy[] {
    return Toys;
  }

  getToyData(id: number): Toy | null {
    if (id < 0 || id >= Toys.length) {
      return null;
    }
    return Toys[id];
  }

  constructor() { }
}
