import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendororderhistoryComponent } from './vendororderhistory.component';

describe('VendororderhistoryComponent', () => {
  let component: VendororderhistoryComponent;
  let fixture: ComponentFixture<VendororderhistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendororderhistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendororderhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
