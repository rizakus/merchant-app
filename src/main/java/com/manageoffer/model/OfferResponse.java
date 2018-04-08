package com.manageoffer.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.manageoffer.service.exception.OfferException;

public class OfferResponse {
    private int responseCode;
    private String responseMessage;
    private Object data;

    public OfferResponse() {
        this.responseCode = 200;
    }

    public OfferResponse(Object data) {
        this.data = data;
        this.responseCode = 200;
    }

    public OfferResponse(OfferException exception) {
        this.responseCode = exception.getErrorCode();
        this.responseMessage = exception.getErrorMessage();
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
