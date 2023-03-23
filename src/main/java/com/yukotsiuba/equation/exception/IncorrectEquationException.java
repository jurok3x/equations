package com.yukotsiuba.equation.exception;

public class IncorrectEquationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IncorrectEquationException(String message) {
        super(message);
    }

    public IncorrectEquationException(String message, Throwable cause) {
        super(message, cause);
    }
}
