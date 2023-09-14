import { Injectable } from '@angular/core';
import { Developers } from './mock-developer-list';

export interface Developer {
  name: string;
  id: number;
  qualification : string;
  salary : number;
}

@Injectable({
  providedIn: 'root'
})
export class DevelopersService {
  private developers: Developer[] = Developers;

  constructor() { }

  getDevelopers() {
    return this.developers;
  }

  getDeveloper(id : number) {
    let dev = this.developers.find(d => d.id == id);
    if (!dev) {
      return null;
    }
    return dev;
  }

  getNextDeveloperId(id : number) {
    let idx = this.developers.findIndex(d => d.id == id);

    if (idx == -1) {
      return -1;
    }

    if (idx == this.developers.length - 1) {
      return this.developers[0].id;
    }

    return this.developers[idx + 1].id;
  }
}
