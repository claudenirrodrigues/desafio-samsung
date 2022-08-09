package com.samsung.evaluation.api.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class DocumentQuotationDto implements Serializable{
	
	private static final long serialVersionUID = 3690417726719028321L;
	
	private Integer documentId;
	private String documentNumber;
	private Date documentDate;
	private String currencyCode;
	private String currencyDesc;
	private Double documentValue;
	private Double valueUSD;
	private Double valuePEN;
	private Double valueBRL;
	
	
}
