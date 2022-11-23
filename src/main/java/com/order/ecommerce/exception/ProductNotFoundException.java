package com.order.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {

    private String errorCode;

    private Object errorObject;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ProductNotFoundException(String errorCode, String message, Object errorObject) {
        super(message);
        this.errorCode = errorCode;
        this.errorObject = errorObject;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getErrorObject() {
        return errorObject;
    }
}