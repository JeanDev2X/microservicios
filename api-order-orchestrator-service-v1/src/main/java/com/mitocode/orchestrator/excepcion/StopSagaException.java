package com.mitocode.orchestrator.excepcion;

public class StopSagaException extends RuntimeException {
    public StopSagaException(String message, Exception ex) {
        super(message, ex);
    }
}
