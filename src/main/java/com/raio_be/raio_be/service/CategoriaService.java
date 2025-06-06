package com.raio_be.raio_be.service;

import java.util.List;
import java.util.Optional;
import com.raio_be.raio_be.model.Categoria;

public interface CategoriaService {

    List<Categoria> getAllCategorias();

    Optional<Categoria> getCategoriaById(Long id);

    Categoria saveCategoria(Categoria categoria);

    Categoria updateCategoria(Long id, Categoria categoria);

    void deleteCategoria(Long id);
}
