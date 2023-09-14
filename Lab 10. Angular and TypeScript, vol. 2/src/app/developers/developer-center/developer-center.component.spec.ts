import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeveloperCenterComponent } from './developer-center.component';

describe('DeveloperCenterComponent', () => {
  let component: DeveloperCenterComponent;
  let fixture: ComponentFixture<DeveloperCenterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeveloperCenterComponent]
    });
    fixture = TestBed.createComponent(DeveloperCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
