package com.yukotsiuba.equation.exception;

public class BadParameterException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadParameterException(String message) {
        super(message);
    }

    public BadParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
