package com.samsung.evaluation.api.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class CurrencyQuotation implements Serializable{
	
	private static final long serialVersionUID = 6003357526877160315L;
	
	private Double valueUSD;
	private Double valuePEN;
	private Double valueBRL;

}
