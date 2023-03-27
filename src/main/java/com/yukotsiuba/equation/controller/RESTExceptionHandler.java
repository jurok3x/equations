package com.yukotsiuba.equation.controller;

import com.yukotsiuba.equation.exception.APIException;
import com.yukotsiuba.equation.exception.BadRootException;
import com.yukotsiuba.equation.exception.IncorrectEquationException;
import com.yukotsiuba.equation.exception.ResourceNotFoundException;
import com.yukotsiuba.equation.utils.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Resource Not Found.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);

        return ResponseEntityBuilder.build(apiException);
    }

    @ExceptionHandler(value = { BadRootException.class })
    protected ResponseEntity<Object> handleBadRootException(BadRootException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Root is incorrect.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);

        return ResponseEntityBuilder.build(apiException);
    }

    @ExceptionHandler(value = { IncorrectEquationException.class })
    protected ResponseEntity<Object> handleIncorrectEquationException(IncorrectEquationException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Equation is incorrect.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);

        return ResponseEntityBuilder.build(apiException);
    }
}
