package com.raio_be.raio_be.repository;

import com.raio_be.raio_be.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByTituloCategoria(String tituloCategoria);
}
