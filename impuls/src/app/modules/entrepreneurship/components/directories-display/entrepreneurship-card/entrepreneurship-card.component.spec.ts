import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrepreneurshipCardComponent } from './entrepreneurship-card.component';

describe('EntrepreneurshipCardComponent', () => {
  let component: EntrepreneurshipCardComponent;
  let fixture: ComponentFixture<EntrepreneurshipCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EntrepreneurshipCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntrepreneurshipCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
