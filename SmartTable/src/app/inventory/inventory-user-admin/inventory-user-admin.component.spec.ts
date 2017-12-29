import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryUserAdminComponent } from './inventory-user-admin.component';

describe('InventoryUserAdminComponent', () => {
  let component: InventoryUserAdminComponent;
  let fixture: ComponentFixture<InventoryUserAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryUserAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
