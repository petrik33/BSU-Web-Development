import { Component, OnInit } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { from, of } from 'rxjs';
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
          const developer$ = from(this.service.getDeveloper(id));
          return developer$
        }

        return of(null);
      })
    ).subscribe((developer) => {
      this.developer = developer
    });
  }

  async onDeleteDeveloper() {
    if (this.developer == null) {
      return;
    }

    await this.service.deleteDeveloper(this.developer.id);
    this.router.navigate(['../../'], { relativeTo: this.route });
  }

  async onNextDeveloper() {
    if (!this.developer) {
      return
    }

    const next = await this.service.getNextDeveloper(this.developer.id);
    this.router.navigate(['../', next?.id], { relativeTo: this.route });
  }
}
