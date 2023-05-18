import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToyCenterComponent } from './toy-center.component';

describe('ToyCenterComponent', () => {
  let component: ToyCenterComponent;
  let fixture: ComponentFixture<ToyCenterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ToyCenterComponent]
    });
    fixture = TestBed.createComponent(ToyCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
