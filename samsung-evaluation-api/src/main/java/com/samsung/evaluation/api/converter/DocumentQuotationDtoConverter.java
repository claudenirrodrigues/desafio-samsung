package com.samsung.evaluation.api.converter;

import java.util.Date;

import com.samsung.evaluation.api.dto.DocumentQuotationDto;
import com.samsung.evaluation.api.model.Currency;
import com.samsung.evaluation.api.model.CurrencyQuotation;
import com.samsung.evaluation.api.model.Document;

public class DocumentQuotationDtoConverter {

	public static DocumentQuotationDto converter(Document document, Currency currency, CurrencyQuotation currencyQuotation) {

		DocumentQuotationDto dto = new DocumentQuotationDto();

		dto.setDocumentId(document.getDocumentId());
		dto.setDocumentNumber(document.getDocumentNumber());
		dto.setDocumentDate(document.getDocumentDate());
		dto.setCurrencyCode(document.getCurrencyCode());
		dto.setDocumentValue(document.getDocumentValue());

		dto.setCurrencyDesc(currency.getCurrencyDesc());
		
		dto.setValueUSD(currencyQuotation.getValueUSD());
		dto.setValuePEN(currencyQuotation.getValuePEN());
		dto.setValueBRL(currencyQuotation.getValueBRL());

		return dto;
	}
}
