package com.raio_be.raio_be.exception;

public class MensajeReverberadoNotFoundException extends RuntimeException {
    public MensajeReverberadoNotFoundException(Integer id) {
        super("No se encontró la reverberación con ID: " + id);
    }
}
