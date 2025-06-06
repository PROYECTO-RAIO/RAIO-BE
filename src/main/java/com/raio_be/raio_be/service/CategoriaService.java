package com.raio_be.raio_be.service;

import java.util.List;
import java.util.Optional;
import com.raio_be.raio_be.model.Categoria;

public interface CategoriaService {

    List<Categoria> listar();

    Optional<Categoria> obtenerPorId(Long id);

    Categoria guardar(Categoria categoria);

    Categoria actualizar(Long id, Categoria categoria);

    void eliminar(Long id);
}
