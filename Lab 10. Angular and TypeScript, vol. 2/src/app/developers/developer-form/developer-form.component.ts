import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {DevelopersService, IDeveloper} from '../service/developers.service';

@Component({
  selector: 'app-developer-form',
  templateUrl: './developer-form.component.html',
  styleUrls: ['./developer-form.component.css']
})
export class DeveloperFormComponent implements OnInit {
  developer: IDeveloper = {name: '', id: '', salary: 0};

  constructor(private service: DevelopersService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.getDeveloper(id).subscribe(queriedDeveloper => {
        if (queriedDeveloper) {
          this.developer = queriedDeveloper;
        }
      }, error => {
        // Обработка ошибок, например, если разработчик не найден
        console.error(error);
      });
    }
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }

    if (this.developer.id && this.developer.id != 'new') {
      this.service.updateDeveloper(this.developer);
    } else {
      this.service.addDeveloper(this.developer);
    }

    this.router.navigate(['../../'], {relativeTo: this.route});
  }
}
