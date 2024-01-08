package com.brq.runthebank.services.exceptions;

public class DocumentDuplicationException extends RuntimeException {
    public DocumentDuplicationException(String documento) {
        super(documento);
    }
}
