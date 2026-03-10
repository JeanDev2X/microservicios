package com.mitocode.orchestrator.excepcion;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message, Exception ex) {
        super(message, ex);
    }
}
