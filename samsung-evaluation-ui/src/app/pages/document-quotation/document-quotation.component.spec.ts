import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentQuotationComponent } from './document-quotation.component';

describe('DocumentQuotationComponent', () => {
  let component: DocumentQuotationComponent;
  let fixture: ComponentFixture<DocumentQuotationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocumentQuotationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DocumentQuotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
