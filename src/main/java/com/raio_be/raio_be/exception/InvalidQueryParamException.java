package com.raio_be.raio_be.exception;

public class InvalidQueryParamException extends RuntimeException {
    public InvalidQueryParamException(String message) {
        super(message);
    }
}
