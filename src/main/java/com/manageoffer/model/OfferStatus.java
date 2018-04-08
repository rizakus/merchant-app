package com.manageoffer.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OfferStatus {
	ACTIVE, EXPIRED, CANCELED;
	
	@JsonValue
	 public String toJson() {
       return name();
   }
}
