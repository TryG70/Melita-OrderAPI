package com.melita.orderfulfillmentapi.controller;


import com.melita.orderfulfillmentapi.exception.OrderNotApprovedException;
import com.melita.orderfulfillmentapi.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotApprovedException.class)
    public ResponseEntity<Object> OrderNotApproved(OrderNotApprovedException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), EXPECTATION_FAILED);
        return new ResponseEntity<>(exceptionResponse, EXPECTATION_FAILED);
    }

}
