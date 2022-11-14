import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutriHomeComponent } from './nutri-home.component';

describe('NutriHomeComponent', () => {
  let component: NutriHomeComponent;
  let fixture: ComponentFixture<NutriHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NutriHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NutriHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
