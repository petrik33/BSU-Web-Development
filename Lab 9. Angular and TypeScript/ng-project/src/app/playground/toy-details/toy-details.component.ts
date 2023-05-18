import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Toy } from 'src/data/mock-toy-list';
import { PlaygroundService } from '../playground.service';

@Component({
  selector: 'app-toy-details',
  templateUrl: './toy-details.component.html',
  styleUrls: ['./toy-details.component.css']
})
export class ToyDetailsComponent implements OnInit {
  toy: Toy | undefined;

  constructor(private playgroundService: PlaygroundService, private route: ActivatedRoute) { }

  ngOnInit() {
    const toyId = +this.route.snapshot.paramMap.get('id')!
    this.toy = this.playgroundService.getToyData(toyId);
  }
}
