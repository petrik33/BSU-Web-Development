import { Component, OnInit } from '@angular/core';
import { Developer, DevelopersService } from '../service/developers.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NgForm } from '@angular/forms';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-developer-form',
  templateUrl: './developer-form.component.html',
  styleUrls: ['./developer-form.component.css']
})
export class DeveloperFormComponent implements OnInit {
  developer: Developer = { name: '', id: 0, qualification: '', salary: 0 };

  constructor(private service: DevelopersService, private router: Router, private route: ActivatedRoute) {}

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
      if (!developer) {
        return
      }

      this.developer = developer
    });
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    
    if (this.developer.id) {
      this.service.updateDeveloper(this.developer);
    } else {
      this.service.addDeveloper(this.developer);
    }

    this.router.navigate(['list'], { relativeTo: this.route.parent });
  }
}