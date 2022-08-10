package com.samsung.evaluation.api.converter;


import com.samsung.evaluation.api.dto.CurrencyDto;
import com.samsung.evaluation.api.model.Currency;

public class CurrencyDtoConverter {

	public static CurrencyDto converter(Currency currency) {

		CurrencyDto dto = new CurrencyDto();

		dto.setCurrencyCode(currency.getCurrencyCode());
		dto.setCurrencyDesc(currency.getCurrencyDesc());
		
		return dto;
	}
}
