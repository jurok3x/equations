package com.yukotsiuba.equation.exception;

public class EquationAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EquationAlreadyExistsException(String message) {
        super(message);
    }

    public EquationAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
