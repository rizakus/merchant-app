package com.manageoffer.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manageoffer.deserializers.CurrencyDeserializer;

public class OfferParams implements Serializable {
	private static final long serialVersionUID = 3641394595565406468L;
	private String name;
	private Double price;
	private Currency currency;
	private String expireDate;
	private String userDescription;
	
	public OfferParams() {
	}
	
	public OfferParams(String name, Double price, Currency currency, String expireDate, String userDescription) {
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.expireDate = expireDate;
		this.userDescription = userDescription;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonProperty("currency")
	public Currency getCurrency() {
		return currency;
	}
	
	@JsonProperty("currency")
    @JsonDeserialize(using = CurrencyDeserializer.class)
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getUserDescription() {
		return userDescription;
	}
	
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
