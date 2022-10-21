package com.melita.orderfulfillmentapi.exception;

import lombok.Data;

@Data
public class OrderNotApprovedException extends RuntimeException {

    public OrderNotApprovedException(String message) {
        super(message);
    }

}
