package com.samsung.evaluation.api.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class CurrencyDto implements Serializable{
	
	private static final long serialVersionUID = -7983659324642659084L;
	
	private String currencyCode;
	private String currencyDesc;

}
