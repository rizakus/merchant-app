package com.manageoffer.service.exception;

public enum OfferError {
    INVALID_DATE_FORMAT(1001, "Invalid date format. Valid date format is yyyy-MM-dd HH:mm:ss"), 
    INVALID_EXPIRE_DATE(1002, "Expire date is before current date time! Pelase set future date time"), 
    OFFER_ALREADY_CANCELED(1003, "Offer {0} is already canceled"), 
    OFFER_EXPIRED(1004, "Offer {0} is expired"),
    INVALID_OFFER_ID(1005, "Offer {0} cannot be found"),
    NAME_PARAMETER_CAN_NOT_BE_NULL(1006, "Name parameter can not be null or empty"),
    PRICE_PARAMETER_CAN_NOT_BE_NULL(1007, "Price parameter can not be null"),
    CURRENCY_PARAMETER_CAN_NOT_BE_NULL(1008, "Currency parameter can not be null"),
    EXPIRE_DATE_PARAMETER_CAN_NOT_BE_NULL(1009, "Expire date parameter can not be null"),
    USER_DESCRIPTION_PARAMETER_CAN_NOT_BE_NULL(1010, "User description parameter can not be null or empty");

    private int code;
    private String message;

    private OfferError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
