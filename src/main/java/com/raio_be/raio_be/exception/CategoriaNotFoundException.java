package com.raio_be.raio_be.exception;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("Categoría con ID " + id + " no encontrada");
    }
}
