package com.order.ecommerce.exception;

public class OrderNotFoundException extends RuntimeException {


    private String errorCode;

    private Object errorObject;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OrderNotFoundException(String errorCode, String message, Object errorObject) {
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
