// MensajeOriginalNotFoundException.java
package com.raio_be.raio_be.exception;

public class MensajeOriginalNotFoundException extends RuntimeException {
    public MensajeOriginalNotFoundException(Long id) {
        super("No se encontró el mensaje con ID: " + id);
    }
}
