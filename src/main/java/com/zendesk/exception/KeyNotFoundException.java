package com.zendesk.exception;

/**
 * Created by Grace on 9/12/2020.
 */

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String message) {
        super(message);
    }
}
