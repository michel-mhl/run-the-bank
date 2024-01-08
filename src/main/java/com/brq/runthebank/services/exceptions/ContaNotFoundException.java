package com.brq.runthebank.services.exceptions;

public class ContaNotFoundException extends RuntimeException {
    public ContaNotFoundException(String documento) {
        super(documento);
    }



    public ContaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
