package com.raio_be.raio_be.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaValidationTest {

    private Validator validator;

    @BeforeEach
    void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidEmail() {
        Categoria categoria = Categoria.builder()
                .tituloCategoria("Título válido")
                .autorEmailCategoria("correo-no-valido")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("autorEmailCategoria")));
    }

    @Test
    void testTituloCategoriaExceedsMaxLength() {
        String longTitulo = "a".repeat(51);

        Categoria categoria = Categoria.builder()
                .tituloCategoria(longTitulo)
                .autorEmailCategoria("ejemplo@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("tituloCategoria")));
    }

    @Test
    void testFechaInicioEnPasado() {
        Categoria categoria = Categoria.builder()
                .tituloCategoria("Algo")
                .autorEmailCategoria("valido@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().minusDays(1)) // <-- inválido
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("fechaInicio")));
    }

}