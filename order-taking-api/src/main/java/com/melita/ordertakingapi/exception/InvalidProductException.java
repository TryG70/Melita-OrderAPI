package com.melita.ordertakingapi.exception;

import lombok.Data;

@Data
public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {

        super(message);
    }

}
