package com.samsung.evaluation.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.samsung.evaluation.api.converter.CurrencyDtoConverter;
import com.samsung.evaluation.api.dto.CurrencyDto;
import com.samsung.evaluation.api.model.Currency;

import reactor.core.publisher.Mono;
@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private WebClient webClient;
	
	@Override
	public List<CurrencyDto> findAll() {
		
		List<CurrencyDto> dtos = new ArrayList<CurrencyDto>();
		
		Mono<List<Currency>> monoCurrencies = this.webClient
				.method(HttpMethod.GET)
				.uri("/currency")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Currency>>() {});
		
		List<Currency> currencies = monoCurrencies.block();
		currencies.forEach(c -> dtos.add(CurrencyDtoConverter.converter(c)));
		
		return dtos;
	}

}
