import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorpendingorderComponent } from './vendorpendingorder.component';

describe('VendorpendingorderComponent', () => {
  let component: VendorpendingorderComponent;
  let fixture: ComponentFixture<VendorpendingorderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendorpendingorderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorpendingorderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
