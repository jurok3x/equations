package com.yukotsiuba.equation.exception;

public class BadRootException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRootException(String message) {
        super(message);
    }

    public BadRootException(String message, Throwable cause) {
        super(message, cause);
    }
}
