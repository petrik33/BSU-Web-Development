import {Component} from '@angular/core';
import {DevelopersService, IDeveloper} from '../service/developers.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-developer-details',
  templateUrl: './developer-details.component.html',
  styleUrls: ['./developer-details.component.css']
})
export class DeveloperDetailsComponent {
  developer: IDeveloper | null = null;

  constructor(private service: DevelopersService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id')
    if (!id) {
      return;
    } else {
      this.service.getDeveloper(id).subscribe(developerData => {
        console.log(developerData);
        this.developer = developerData;
      }, error => {
        console.error(error);
        this.router.navigate(['/']);
      });
    }
  }

  onDeleteDeveloper() {
    if (!this.developer) {
      return
    }
    this.service.deleteDeveloper(this.developer.id)
    this.router.navigate(['../../'], {relativeTo: this.route});
  }
}
