import { Component, Input } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';
import { ActivatedRoute, ParamMap, Route, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
@Component({
  selector: 'app-developer-details',
  templateUrl: './developer-details.component.html',
  styleUrls: ['./developer-details.component.css']
})
export class DeveloperDetailsComponent {
  developer$: Observable<Developer | null> = of(null);
  id: number = 0;

  constructor(private route: ActivatedRoute, private router: Router, private service: DevelopersService) { }

  ngOnInit() {
    this.developer$ = this.route.paramMap.pipe(
      switchMap((value: ParamMap, index: number) => {
        const id = value.get('id');

        if (id) {
          const developer = this.service.getDeveloper(+id);
          if (developer) {
            return of(developer);
          }
        }

        return of(null);
      })
    )
  }
}
