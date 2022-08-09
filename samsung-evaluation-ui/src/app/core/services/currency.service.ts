import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { EMPTY, from, Observable } from "rxjs";
import { AppConfigurationService } from './app-configuration.service';
import { catchError, retry, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient, private appConfig: AppConfigurationService){}
  
  findAll(): Observable<any[]>  {
    return this.http.get<any []>(this.appConfig.baseUrl + "/documentsQuotations/currencies").pipe(
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
