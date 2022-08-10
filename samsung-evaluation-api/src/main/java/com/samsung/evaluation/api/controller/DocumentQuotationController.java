package com.samsung.evaluation.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.evaluation.api.dto.CurrencyDto;
import com.samsung.evaluation.api.dto.DocumentQuotationDto;
import com.samsung.evaluation.api.service.CurrencyService;
import com.samsung.evaluation.api.service.DocumentQuotationService;

@RestController
@RequestMapping("/documentsQuotations")
public class DocumentQuotationController {
	
		@Autowired
		private DocumentQuotationService documentQuotationService;
		
		@Autowired
		private CurrencyService currencyService;
		
		@GetMapping
	    public ResponseEntity<List<DocumentQuotationDto>> findAll() {
	        List<DocumentQuotationDto> documents = documentQuotationService.findAll();
	        return ResponseEntity.ok(documents);
	    }		
		
		@GetMapping("/search")
	    public ResponseEntity<List<DocumentQuotationDto>> findByAnyCriteria(
	    		@RequestParam(value="documentNumber", required=false) Optional<String> documentNumber,
	    		@RequestParam(value="currencyCode", required=false) Optional<String> currencyCode, 
	    		@RequestParam(value="afterDate", required=false) Optional<Date> afterDate,
	    		@RequestParam(value="beforeDate", required=false) Optional<Date> beforeDate) {
	        
			List<DocumentQuotationDto> documents = documentQuotationService
					.findByAnyCriteria(documentNumber, currencyCode, afterDate, beforeDate);
	        return ResponseEntity.ok(documents);
	    }
		
		@GetMapping("/currencies")
	    public ResponseEntity<List<CurrencyDto>> findAllCurrencies() {
	        List<CurrencyDto> currencies = currencyService.findAll();
	        return ResponseEntity.ok(currencies);
	    }
}
