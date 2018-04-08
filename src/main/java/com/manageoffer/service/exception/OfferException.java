package com.manageoffer.service.exception;

public class OfferException extends RuntimeException {
    private static final long serialVersionUID = -3257022882385328074L;
    private int errorCode;
    private String errorMessage;

    public OfferException(OfferError error) {
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
    }

    public OfferException(OfferError error, Object... params) {
        this(error);
        if (params != null)
        {
        	for (int i = 0; i < params.length; i++) {
        		errorMessage = errorMessage.replace("{" + i + "}", String.valueOf(params[i]));
        	}
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
