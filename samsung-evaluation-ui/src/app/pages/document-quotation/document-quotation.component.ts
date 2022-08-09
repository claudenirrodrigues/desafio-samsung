import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DocumentQuotation } from 'src/app/shared/models/document-quotation.model';
import { Filter } from 'src/app/shared/models/filter.model';
import { Currency } from 'src/app/shared/models/currency.model';
import { DocumentQuotationService } from 'src/app/core/services/document-quotation.service';
import { CurrencyService } from 'src/app/core/services/currency.service';

@Component({
  selector: 'app-document-quotation',
  templateUrl: './document-quotation.component.html',
  styleUrls: ['./document-quotation.component.css']
})
export class DocumentQuotationComponent implements OnInit {

  documentsQuotations: DocumentQuotation[] = new Array();
  filter: Filter = new Filter();
  currencies = [] = new Array();

  constructor(public documentQuotationService: DocumentQuotationService,
    public currencyService: CurrencyService) { }

  ngOnInit(): void {
    this.inicializeModels();
    this.loadCurrencies();
  }

  inicializeModels() {
    this.filter = new Filter();
  }

  search(filter: Filter) {
    this.documentQuotationService.search(filter).subscribe(data => {
      if (JSON.stringify(data) != 'null' && JSON.stringify(data) != '{}' && JSON.stringify(data) != '[]' && typeof JSON.stringify(data) != 'undefined') {
          this.documentsQuotations = data;
          console.log(this.documentsQuotations);
      }
    });
  }

  loadCurrencies() {
    this.currencyService.findAll().subscribe((data: Currency[]) => {
      if (JSON.stringify(data) != 'null' && JSON.stringify(data) != '{}' && JSON.stringify(data) != '[]' && typeof JSON.stringify(data) != 'undefined') {
          this.currencies = data.map( (c: Currency) => ({label: c.currencyDesc, value: c.currencyCode}) )
          console.log(this.currencies);
      }
    });

  }

  clearForm(form: NgForm) {
    form.resetForm();
  }

}
