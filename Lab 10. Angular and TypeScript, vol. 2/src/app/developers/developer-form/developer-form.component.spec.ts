import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeveloperFormComponent } from './developer-form.component';

describe('DeveloperFormComponent', () => {
  let component: DeveloperFormComponent;
  let fixture: ComponentFixture<DeveloperFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeveloperFormComponent]
    });
    fixture = TestBed.createComponent(DeveloperFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
