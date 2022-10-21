package com.melita.ordertakingapi.exception;

import lombok.Data;

@Data
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }

}
