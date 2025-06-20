package com.raio_be.raio_be.service;

import com.raio_be.raio_be.exception.CategoriaNotFoundException;
import com.raio_be.raio_be.exception.ConflictException;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.repository.CategoriaRepository;
import com.raio_be.raio_be.util.SimpleSanitizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria sanitizeCategoriaFields(Categoria categoria) {
        categoria.setTituloCategoria(SimpleSanitizer.sanitize(categoria.getTituloCategoria()));
        categoria.setAutorCategoria(SimpleSanitizer.sanitize(categoria.getAutorCategoria()));
        categoria.setDescripcionCategoria(SimpleSanitizer.sanitize(categoria.getDescripcionCategoria()));
        return categoria;
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        categoria = sanitizeCategoriaFields(categoria);

    if (categoriaRepository.existsByTituloCategoria(categoria.getTituloCategoria())) {
        throw new ConflictException("Ya existe una categoría con ese título.");
    }
    return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(id);
        }
         if (categoriaRepository.existsByTituloCategoria(categoria.getTituloCategoria())) {
        throw new ConflictException("Ya existe una categoría con ese título.");
    }
        categoria.setId(id);
        categoria = sanitizeCategoriaFields(categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }
}
