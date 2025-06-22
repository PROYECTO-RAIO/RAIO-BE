package com.raio_be.raio_be.DTO;

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

class CategoriaDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidEmail() {
        CategoriaDTO categoria = CategoriaDTO.builder()
                .tituloCategoria("Título válido")
                .autorEmailCategoria("correo-no-valido")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoria);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("autorEmailCategoria")));
    }

    @Test
    void testTituloCategoriaExceedsMaxLength() {
        String longTitulo = "a".repeat(51);

        CategoriaDTO categoria = CategoriaDTO.builder()
                .tituloCategoria(longTitulo)
                .autorEmailCategoria("ejemplo@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoria);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("tituloCategoria")));
    }

    @Test
    void testTituloCategoriaIsBlank() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .tituloCategoria(" ")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("tituloCategoria")));
    }

    @Test
    void testListaCorreoInvalidEmail() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .tituloCategoria("Título válido")
                .autorEmailCategoria("valido@email.com")
                .listaCorreo("no-es-email")
                .estadoDeActividad(true)
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("listaCorreo")));
    }

    @Test
    void testArchivoUrlInvalidPattern() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .tituloCategoria("Título válido")
                .autorEmailCategoria("valido@email.com")
                .archivoUrl("no-es-una-url")
                .estadoDeActividad(true)
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("archivoUrl")));
    }

    @Test
    void testDescripcionCategoriaExceedsMaxLength() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .tituloCategoria("Título válido")
                .autorEmailCategoria("valido@email.com")
                .descripcionCategoria("a".repeat(1001))
                .estadoDeActividad(true)
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("descripcionCategoria")));
    }

    @Test
    void testValidCategoriaDTO() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .tituloCategoria("Categoría válida")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .descripcionCategoria("Descripción breve")
                .autorCategoria("Nombre del autor")
                .listaCorreo("lista@correo.com")
                .archivoUrl("https://example.com/archivo.pdf")
                .build();

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}