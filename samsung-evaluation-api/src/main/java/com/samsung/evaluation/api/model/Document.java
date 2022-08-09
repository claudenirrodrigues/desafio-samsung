package com.samsung.evaluation.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class Document implements Serializable{
	
	private static final long serialVersionUID = 3695935933797778742L;
	
	private Integer documentId;
	private String documentNumber;
	private String notaFiscal;
	private Date documentDate;
	private Double documentValue;
	private String currencyCode;
	
	

}
