package com.samsung.evaluation.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Quotation implements Serializable, Comparable<Quotation> {
	
	private static final long serialVersionUID = 643850642382395400L;
	
	private String fromCurrencyCode;
	private String toCurrencyCode;
	private Double cotacao;
	private Date dataHoraCotacao;
	
	@Override
	public int compareTo(Quotation quotation) {
		return getDataHoraCotacao().compareTo(quotation.getDataHoraCotacao());
	}

	
}
