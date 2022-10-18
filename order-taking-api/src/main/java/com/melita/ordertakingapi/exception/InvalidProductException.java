package com.melita.ordertakingapi.exception;

import lombok.Data;

@Data
public class InvalidProductException extends RuntimeException {

    private String message;

    public InvalidProductException(String message) {

        this.message = message;
    }

}
