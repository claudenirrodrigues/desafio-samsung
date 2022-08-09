import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DocumentQuotationComponent } from './pages/document-quotation/document-quotation.component';

const routes: Routes = [
  {
    path:'',
    component: DocumentQuotationComponent,
    children: [
      {path:'', redirectTo: 'document-quotation', pathMatch: 'full'}
    ]
  },
  { path:'document-quotation', component: DocumentQuotationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
