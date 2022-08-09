import { Injectable } from '@angular/core';
import { Filter } from 'src/app/shared/models/filter.model';
import { DocumentQuotation } from 'src/app/shared/models/document-quotation.model';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { EMPTY, from, Observable } from "rxjs";
import { AppConfigurationService } from './app-configuration.service';
import { catchError, retry, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class DocumentQuotationService {
  

  constructor(private http: HttpClient, private appConfig: AppConfigurationService){}
  
  findAll(): Observable<DocumentQuotation[]>  {
    return this.http.get<DocumentQuotation []>(this.appConfig.baseUrl + "/documentsQuotations").pipe(
      retry(1),
      map((obj)=>obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  search(filter: Filter): Observable<DocumentQuotation[]>  {
    return this.http.get<DocumentQuotation[]>(this.appConfig.baseUrl + 
      "/documentsQuotations/search?documentNumber=" +
      filter.documentNumber+ "&afterDate=" + 
      filter.afterDate + "&beforeDate="+ 
      filter.beforeDate + "&currencyCode=" + 
      filter.currencyCode)
      .pipe(
      retry(1),
      map((obj)=>obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    console.log(e);
    return EMPTY;
  }
}
