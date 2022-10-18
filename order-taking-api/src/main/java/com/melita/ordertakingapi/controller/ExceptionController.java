package com.melita.ordertakingapi.controller;

import com.melita.ordertakingapi.exception.InvalidProductException;
import com.melita.ordertakingapi.exception.NotAuthorizedException;
import com.melita.ordertakingapi.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<Object> invalidProduct(InvalidProductException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), FORBIDDEN);
        return new ResponseEntity<>(exceptionResponse, FORBIDDEN);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Object> notAuthorized(NotAuthorizedException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), UNAUTHORIZED);
        return new ResponseEntity<>(exceptionResponse, UNAUTHORIZED);
    }


}
