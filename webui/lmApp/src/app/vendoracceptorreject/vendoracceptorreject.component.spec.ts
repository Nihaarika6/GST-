import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendoracceptorrejectComponent } from './vendoracceptorreject.component';

describe('VendoracceptorrejectComponent', () => {
  let component: VendoracceptorrejectComponent;
  let fixture: ComponentFixture<VendoracceptorrejectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendoracceptorrejectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendoracceptorrejectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
