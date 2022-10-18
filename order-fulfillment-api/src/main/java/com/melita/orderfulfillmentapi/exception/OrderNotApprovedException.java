package com.melita.orderfulfillmentapi.exception;

import lombok.Data;

@Data
public class OrderNotApprovedException extends RuntimeException {

    private String message;
    public OrderNotApprovedException(String message) {
        this.message = message;
    }

}
