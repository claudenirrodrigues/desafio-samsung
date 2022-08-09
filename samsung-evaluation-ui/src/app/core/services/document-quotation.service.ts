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
      "/documentsQuotations/search"+ this.buildQueryString(filter)).pipe(
      retry(1),
      map((obj)=>obj),
      catchError((e) => this.errorHandler(e))
    );
  }
  
  buildQueryString(filter: Filter) {
    let queryString = "";
    if (this.isValidParmeter(filter.documentNumber)) {
      queryString = "documentNumber=" + filter.documentNumber; 
    }
    if (this.isValidParmeter(filter.afterDate)) {
      if(this.isValidParmeter(queryString)){
        queryString =  queryString + "&afterDate=" + filter.afterDate;
      }else{
        queryString = queryString + "afterDate=" + filter.afterDate;      
      }
    }
    if (this.isValidParmeter(filter.beforeDate)) {
      if(this.isValidParmeter(queryString)){
        queryString =  queryString + "&beforeDate=" + filter.beforeDate;
      }else{
        queryString = queryString + "beforeDate=" + filter.beforeDate;      
      }
    }
    if (this.isValidParmeter(filter.currencyCode)) {
      if(this.isValidParmeter(queryString)){
        queryString =  queryString + "&currencyCode=" + filter.currencyCode;
      }else{
        queryString = queryString + "currencyCode=" + filter.currencyCode;      
      }
    }
    return this.isValidParmeter(queryString) ? "?" + queryString : "";
  }

  isValidParmeter(param: any) {
    return JSON.stringify(param) != 'null' && 
            JSON.stringify(param) != '{}' && 
            JSON.stringify(param) != '[]' && 
            typeof JSON.stringify(param) != 'undefined'
  }

  errorHandler(e: any): Observable<any> {
    console.log(e);
    return EMPTY;
  }
}
