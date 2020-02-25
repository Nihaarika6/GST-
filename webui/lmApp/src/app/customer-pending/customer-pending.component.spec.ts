import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPendingComponent } from './customer-pending.component';

describe('CustomerPendingComponent', () => {
  let component: CustomerPendingComponent;
  let fixture: ComponentFixture<CustomerPendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerPendingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
