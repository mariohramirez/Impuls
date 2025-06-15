import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectoriesDisplayComponent } from './directories-display.component';

describe('DirectoriesDisplayComponent', () => {
  let component: DirectoriesDisplayComponent;
  let fixture: ComponentFixture<DirectoriesDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DirectoriesDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectoriesDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
