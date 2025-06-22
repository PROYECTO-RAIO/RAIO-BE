package com.raio_be.raio_be.repository;

import com.raio_be.raio_be.model.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByTituloCategoria(String tituloCategoria);
    Optional<Categoria> findByTituloCategoria(String tituloCategoria);
}
