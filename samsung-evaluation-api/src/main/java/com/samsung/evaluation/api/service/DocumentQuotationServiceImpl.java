package com.samsung.evaluation.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.reactive.function.client.WebClient;

import com.samsung.evaluation.api.converter.DocumentQuotationDtoConverter;
import com.samsung.evaluation.api.dto.DocumentQuotationDto;
import com.samsung.evaluation.api.model.Currency;
import com.samsung.evaluation.api.model.CurrencyQuotation;
import com.samsung.evaluation.api.model.Document;
import com.samsung.evaluation.api.model.Quotation;

import reactor.core.publisher.Mono;

@Service
public class DocumentQuotationServiceImpl implements DocumentQuotationService{

	@Autowired
	private WebClient webClient;
	
	@Override
	public List<DocumentQuotationDto> findAll() {
		
		List<Document> documents = findAllDocuments();
		
		return fillDocuments(documents);
		
	}

	private List<DocumentQuotationDto> fillDocuments(List<Document> documents) {
		
		List<DocumentQuotationDto> dtos = new ArrayList<DocumentQuotationDto>();
		
		Mono<List<Currency>> monoCurrencies = this.webClient
				.method(HttpMethod.GET)
				.uri("/currency")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Currency>>() {}); 
		
		Mono<List<Quotation>> monoQuotations = this.webClient
				.method(HttpMethod.GET)
				.uri("/quotation")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Quotation>>() {}); 
		
		 
		
		List<Currency> currencies = monoCurrencies.block();
		List<Quotation> quotations = monoQuotations.block();
		
		documents.forEach(document -> {
			
			Currency currency = getCurrency(document, currencies); 
			CurrencyQuotation currencyQuotation = getCurrencyQuotation(document, currencies, quotations);
			dtos.add(DocumentQuotationDtoConverter.converter(document, currency, currencyQuotation));
		});
		
		
		return dtos;
	}

	private CurrencyQuotation getCurrencyQuotation(Document document, List<Currency> currencies, List<Quotation> quotations) {
		
		CurrencyQuotation currencyQuotation = new CurrencyQuotation();
		
		currencies.forEach(currency -> {
			quotations.stream()
			.filter(q -> isFromToCurrencyCode(q, document, currency))
			.sorted(Comparators.comparable().reversed())
			.findFirst()
			.ifPresentOrElse((q) -> { 
				fillCurrencyQuotation(currencyQuotation, q, document, currency);
			}, () -> {		
				fillCurrencyQuotation(currencyQuotation, null, document, currency);
			});
		});

		return currencyQuotation;
	}

	private void fillCurrencyQuotation(final CurrencyQuotation currencyQuotation, Quotation q, Document document, Currency currency) {
		
		switch (currency.getCurrencyCode()) {
			case "USD":
				currencyQuotation.setValueUSD(getConversionValue(q, document, currency));
				break;
				
			case "PEN":
				currencyQuotation.setValuePEN(getConversionValue(q, document, currency));
				break;
				
			case "BRL":
				currencyQuotation.setValueBRL(getConversionValue(q, document, currency));
				break;
			
			default:
				break;
		}
		
	}

	private Double getConversionValue(Quotation quotation, Document document, Currency currency) {
		
		if(!document.getCurrencyCode().equals(currency.getCurrencyCode()) && quotation != null) {
			return document.getDocumentValue() * quotation.getCotacao();
		}
		
		return document.getDocumentValue();
	}

	private boolean isFromToCurrencyCode(Quotation quotation, Document document, Currency currency) {
		return quotation.getFromCurrencyCode().equals(document.getCurrencyCode()) && 
				quotation.getToCurrencyCode().equals(currency.getCurrencyCode());
	}

	private Currency getCurrency(Document document, List<Currency> currencies) {
		
		return currencies.stream()
				.filter(c -> c.getCurrencyCode().equals(document.getCurrencyCode()))
				.findFirst()
				.get();
	}

	private List<Document> findAllDocuments() {
		 
		Mono<List<Document>> monoDocuments = this.webClient
				.method(HttpMethod.GET)
				.uri("/docs")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Document>>() {});
		
		return  monoDocuments.block();
	}

	@Override
	public List<DocumentQuotationDto> findByAnyCriteria(Optional<String> documentNumber, 
			Optional<String> currencyCode, Optional<Date> afterDate, Optional<Date> beforeDate) {
		
		return findAll()
				.stream()
				.filter(document ->  
					(documentNumber.isPresent() ? document.getDocumentNumber().equals(documentNumber.get()) : document.equals(document)) &&
					(currencyCode.isPresent() ? document.getCurrencyCode().equals(currencyCode.get()) : document.equals(document)) &&
					(afterDate.isPresent() ? document.getDocumentDate().after(afterDate.get()) : document.equals(document)) &&
					(beforeDate.isPresent() ? document.getDocumentDate().before(beforeDate.get()) : document.equals(document)))						 
				.collect(Collectors.toList());
	}

	
}
