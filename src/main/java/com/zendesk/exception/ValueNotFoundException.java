package com.zendesk.exception;

/**
 * Created by Grace on 9/12/2020.
 */

public class ValueNotFoundException extends RuntimeException {

    public ValueNotFoundException(String message) {
        super(message);
    }
}
