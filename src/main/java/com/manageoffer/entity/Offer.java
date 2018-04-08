package com.manageoffer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manageoffer.deserializers.CurrencyDeserializer;
import com.manageoffer.model.Currency;
import com.manageoffer.model.OfferStatus;

@Entity(name = "Offer")
@Table(name = "offers")
public class Offer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "currency", nullable = false)
	private Currency currency;
	
	@Column(name = "expire_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireDate;
	
	@Column(name = "cancel_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cancelDate;
	
	@Column(name = "user_description", nullable = false)
	private String userDescription;
	
	@Column(name = "status", nullable = false)
	private OfferStatus status; 
	
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
	
	public Date getExpireDate() {
		return expireDate;
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getUserDescription() {
		return userDescription;
	}
	
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	public OfferStatus getStatus() {
		return status;
	}
	
	public void setStatus(OfferStatus status) {
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
