package com.brq.runthebank.services.exceptions;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException(String message) {
        super(message);
    }

    public SenhaIncorretaException(String message, Throwable cause) {
        super(message, cause);
    }
}

