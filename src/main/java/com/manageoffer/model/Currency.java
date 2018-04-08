package com.manageoffer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Currency {
	GBP("GBP"),
	USD("USD"),
	EUR("EUR"),
	TRY("TRY");
	
	private String currency;
	
	private Currency(String currency) {
		this.currency = currency;
	}
	
	@JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }
	
	public static Currency fromCurrency(String currency) {
        if (currency != null) {
            for (Currency type : Currency.values()) {
                if (currency.equalsIgnoreCase(type.currency)) {
                    return type;
                }
            }
        }
        return null;
    }
}
