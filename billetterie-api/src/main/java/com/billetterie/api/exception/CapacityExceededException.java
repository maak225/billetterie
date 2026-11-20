package com.billetterie.api.exception;

public class CapacityExceededException extends BusinessRuleException {
    public CapacityExceededException(String message) {
        super(message);
    }
}