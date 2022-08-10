package com.samsung.evaluation.api.service;

import java.util.List;

import com.samsung.evaluation.api.dto.CurrencyDto;

public interface CurrencyService {
	public List<CurrencyDto> findAll();
}
