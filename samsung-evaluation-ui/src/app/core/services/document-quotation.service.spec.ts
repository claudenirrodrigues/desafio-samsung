import { TestBed } from '@angular/core/testing';

import { DocumentQuotationService } from './document-quotation.service';

describe('DocumentQuotationService', () => {
  let service: DocumentQuotationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DocumentQuotationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
