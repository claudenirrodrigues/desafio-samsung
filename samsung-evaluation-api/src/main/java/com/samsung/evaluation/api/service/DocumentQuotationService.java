package com.samsung.evaluation.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.samsung.evaluation.api.dto.CurrencyDto;
import com.samsung.evaluation.api.dto.DocumentQuotationDto;

public interface DocumentQuotationService {
	
	public List<DocumentQuotationDto> findAll();
	public List<DocumentQuotationDto> findByAnyCriteria(Optional<String> documentNumber, Optional<String> currencyCode, Optional<Date> afterDate, Optional<Date> beforeDate);
}
