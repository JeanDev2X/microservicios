package com.mitocode.payment.error;

import lombok.Getter;

@Getter
public class ChargeErrorException extends RuntimeException {

    private final int httpStatusCode;
    private final String status;

    public ChargeErrorException(String message, String status, int httpStatusCode) {
        super(message);
        this.status = status;
        this.httpStatusCode = httpStatusCode;
    }
}
