import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomJumbotronComponent } from './custom-jumbotron.component';

describe('CustomJumbotronComponent', () => {
  let component: CustomJumbotronComponent;
  let fixture: ComponentFixture<CustomJumbotronComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomJumbotronComponent]
    });
    fixture = TestBed.createComponent(CustomJumbotronComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
