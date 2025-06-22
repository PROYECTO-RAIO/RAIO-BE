package com.raio_be.raio_be.service;

import com.raio_be.raio_be.exception.ConflictException;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.repository.CategoriaRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

        verify(categoriaRepository).findAll();
    }

    @Test
    void testGetCategoriaById_whenExists() {
        Categoria cat = new Categoria();
        cat.setId(1L);
        cat.setTituloCategoria("Única");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(cat));

        Optional<Categoria> result = categoriaService.getCategoriaById(1L);

        assertTrue(result.isPresent());
        assertEquals("Única", result.get().getTituloCategoria());

        verify(categoriaRepository).findById(1L);
    }

    @Test
    void testGetCategoriaById_whenNotFound() {
        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Categoria> result = categoriaService.getCategoriaById(999L);

        assertFalse(result.isPresent());

        verify(categoriaRepository).findById(999L);
    }

    @Test
    void testSaveCategoria_withValidFields() {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(10);

        Categoria categoria = Categoria.builder()
                .tituloCategoria("Título válido")
                .autorCategoria("Autor válido")
                .descripcionCategoria("Descripción válida")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .fechaInicio(startDate)
                .fechaFinal(endDate)
                .build();

        Categoria savedCategoria = Categoria.builder()
                .id(1L)
                .tituloCategoria("Título válido")
                .autorCategoria("Autor válido")
                .descripcionCategoria("Descripción válida")
                .autorEmailCategoria("autor@email.com")
                .estadoDeActividad(true)
                .fechaInicio(startDate)
                .fechaFinal(endDate)
                .build();

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(savedCategoria);

        Categoria result = categoriaService.saveCategoria(categoria);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Título válido", result.getTituloCategoria());

        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testUpdateCategoria_withValidFields() {
        Long id = 2L;

        Categoria categoriaActualizada = Categoria.builder()
                .tituloCategoria("Nuevo título")
                .autorCategoria("Nuevo autor")
                .descripcionCategoria("Nueva descripción")
                .build();

        Categoria existente = Categoria.builder()
                .id(id)
                .tituloCategoria("Título viejo")
                .autorCategoria("Autor viejo")
                .descripcionCategoria("Descripción vieja")
                .build();

        Categoria guardada = Categoria.builder()
                .id(id)
                .tituloCategoria("Nuevo título")
                .autorCategoria("Nuevo autor")
                .descripcionCategoria("Nueva descripción")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(existente));

        when(categoriaRepository.findByTituloCategoria("Nuevo título"))
                .thenReturn(Optional.of(guardada));

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(guardada);

        Categoria result = categoriaService.updateCategoria(id, categoriaActualizada);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nuevo título", result.getTituloCategoria());
        assertEquals("Nuevo autor", result.getAutorCategoria());
        assertEquals("Nueva descripción", result.getDescripcionCategoria());

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).findByTituloCategoria("Nuevo título");
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testDeleteCategoriaById() {
        Long id = 3L;

        when(categoriaRepository.existsById(id)).thenReturn(true);
        doNothing().when(categoriaRepository).deleteById(id);

        categoriaService.deleteCategoria(id);

        verify(categoriaRepository).existsById(id);
        verify(categoriaRepository).deleteById(id);
    }

    @Test
    void testUpdateCategoria_withTituloConflict_throwsConflictException() {
        Long id = 2L;

        Categoria categoriaActualizada = Categoria.builder()
                .tituloCategoria("Título en conflicto")
                .autorCategoria("Nuevo autor")
                .descripcionCategoria("Nueva descripción")
                .build();

        Categoria existente = Categoria.builder()
                .id(id)
                .tituloCategoria("Título viejo")
                .autorCategoria("Autor viejo")
                .descripcionCategoria("Descripción vieja")
                .build();

        Categoria otraCategoriaConMismoTitulo = Categoria.builder()
                .id(99L) // distinto ID para simular conflicto
                .tituloCategoria("Título en conflicto")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(existente));

        when(categoriaRepository.findByTituloCategoria("Título en conflicto"))
                .thenReturn(Optional.of(otraCategoriaConMismoTitulo));

        ConflictException thrown = assertThrows(
                ConflictException.class,
                () -> categoriaService.updateCategoria(id, categoriaActualizada));

        assertEquals("Ya existe otra categoría con ese título.", thrown.getMessage());

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).findByTituloCategoria("Título en conflicto");
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }
}