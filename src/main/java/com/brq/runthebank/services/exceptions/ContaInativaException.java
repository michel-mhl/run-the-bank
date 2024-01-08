package com.brq.runthebank.services.exceptions;

public class ContaInativaException extends RuntimeException {
    public ContaInativaException(String documento) {
        super(documento);
    }



    public ContaInativaException(String message, Throwable cause) {
        super(message, cause);
    }
}
