package com.melita.orderapprovalapi.exception;

import lombok.Data;

@Data
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

}
