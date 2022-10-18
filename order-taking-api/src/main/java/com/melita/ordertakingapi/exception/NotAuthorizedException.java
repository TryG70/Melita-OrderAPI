package com.melita.ordertakingapi.exception;

import lombok.Data;

@Data
public class NotAuthorizedException extends RuntimeException {

    private String message;
    public NotAuthorizedException(String message) {
        this.message = message;
    }

}
