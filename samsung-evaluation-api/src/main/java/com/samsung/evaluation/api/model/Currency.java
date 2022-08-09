package com.samsung.evaluation.api.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Currency implements Serializable{
	
	private static final long serialVersionUID = -5538086763073535005L;
	
	private Integer currencyId;
	private String currencyCode;
	private String currencyDesc;
	
	
}
