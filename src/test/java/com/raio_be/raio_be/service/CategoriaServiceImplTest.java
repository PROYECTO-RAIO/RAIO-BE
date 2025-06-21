package com.raio_be.raio_be.service;

import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.repository.CategoriaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategorias() {
        Categoria cat1 = new Categoria();
        cat1.setId(1L);
        cat1.setTituloCategoria("Primera");

        Categoria cat2 = new Categoria();
        cat2.setId(2L);
        cat2.setTituloCategoria("Segunda");

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(cat1, cat2));

        List<Categoria> categorias = categoriaService.getAllCategorias();

        assertEquals(2, categorias.size());
        assertEquals("Primera", categorias.get(0).getTituloCategoria());
    }

    @Test
    void testGetCategoriaById() {
        Categoria cat = new Categoria();
        cat.setId(1L);
        cat.setTituloCategoria("Única");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(cat));

        Optional<Categoria> result = categoriaService.getCategoriaById(1L);

        assertTrue(result.isPresent());
        assertEquals("Única", result.get().getTituloCategoria());
    }

    @Test
    void testGetCategoriaByIdNotFound() {
        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Categoria> result = categoriaService.getCategoriaById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveCategoria_withValidFields() {
        Categoria categoria = Categoria.builder()
                .tituloCategoria("Título válido")
                .autorCategoria("Autor válido")
                .descripcionCategoria("Descripción válida")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(10))
                .build();

        Categoria savedCategoria = Categoria.builder()
                .id(1L)
                .tituloCategoria("Título válido")
                .autorCategoria("Autor válido")
                .descripcionCategoria("Descripción válida")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(10))
                .build();

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(savedCategoria);

        Categoria result = categoriaService.saveCategoria(categoria);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testUpdateCategoria_withValidFields() {
        Long id = 2L;
        Categoria input = Categoria.builder()
                .tituloCategoria("Nuevo título")
                .autorCategoria("Nuevo autor")
                .descripcionCategoria("Nueva descripción")
                .autorEmailCategoria("nuevo@email.com")
                .estadoDeActividad(false)
                .fechaInicio(LocalDate.now().plusDays(2))
                .fechaFinal(LocalDate.now().plusDays(5))
                .build();

        Categoria updated = Categoria.builder()
                .id(id)
                .tituloCategoria("Nuevo título")
                .autorCategoria("Nuevo autor")
                .descripcionCategoria("Nueva descripción")
                .autorEmailCategoria("nuevo@email.com")
                .estadoDeActividad(false)
                .fechaInicio(LocalDate.now().plusDays(2))
                .fechaFinal(LocalDate.now().plusDays(5))
                .build();

        when(categoriaRepository.existsById(id)).thenReturn(true);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(updated);

        Categoria result = categoriaService.updateCategoria(id, input);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nuevo título", result.getTituloCategoria());
        assertEquals("nuevo@email.com", result.getAutorEmailCategoria());
    }

    @Test
    void testGetCategoriaById_whenExists() {
        Long id = 1L;
        Categoria categoria = Categoria.builder()
                .id(id)
                .tituloCategoria("Ejemplo")
                .autorEmailCategoria("ejemplo@email.com")
                .estadoDeActividad(true)
                .fechaInicio(LocalDate.now().plusDays(1))
                .fechaFinal(LocalDate.now().plusDays(2))
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        Optional<Categoria> result = categoriaService.getCategoriaById(id);

        assertTrue(result.isPresent());
        assertEquals("Ejemplo", result.get().getTituloCategoria());
    }

    @Test
    void testGetCategoriaById_whenNotExists() {
        Long id = 999L;

        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Categoria> result = categoriaService.getCategoriaById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteCategoriaById() {
        Long id = 3L;

        doNothing().when(categoriaRepository).deleteById(id);

        categoriaService.deleteCategoria(id);

        verify(categoriaRepository).deleteById(id);
    }

}
