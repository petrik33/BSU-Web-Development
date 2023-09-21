import { Component, OnInit } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
@Component({
  selector: 'app-developer-details',
  templateUrl: './developer-details.component.html',
  styleUrls: ['./developer-details.component.css']
})
export class DeveloperDetailsComponent implements OnInit {
  developer: Developer | null = null;

  constructor(public service: DevelopersService, private route: ActivatedRoute, private router : Router) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((value: ParamMap) => {
        const id = value.get('id');

        if (id) {
          const developer$ = this.service.getDeveloper(+id);
          return developer$
        }

        return of(null);
      })
    ).subscribe((developer) => {
      this.developer = developer
    });
  }

  onDeleteDeveloper() {
    if (this.developer == null) {
      return;
    }

    this.service.deleteDeveloper(this.developer.id);
    this.router.navigate(['list'], { relativeTo: this.route.parent });
  }
}
