package com.melita.orderapprovalapi.exception;

import lombok.Data;

@Data
public class OrderNotFoundException extends RuntimeException {
    private String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

}
