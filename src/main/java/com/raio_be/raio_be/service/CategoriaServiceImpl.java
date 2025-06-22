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

        Optional<Categoria> existente = categoriaRepository.findByTituloCategoria(categoria.getTituloCategoria());

        if (existente.isPresent() && !existente.get().getId().equals(categoria.getId())) {
            throw new ConflictException("Ya existe una categoría con ese título.");
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoriaActualizada) {
        categoriaActualizada = sanitizeCategoriaFields(categoriaActualizada);

        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        Optional<Categoria> categoriaConMismoTitulo = categoriaRepository
                .findByTituloCategoria(categoriaActualizada.getTituloCategoria());

        if (categoriaConMismoTitulo.isPresent() && !categoriaConMismoTitulo.get().getId().equals(id)) {
            throw new ConflictException("Ya existe otra categoría con ese título.");
        }

        existente.setTituloCategoria(categoriaActualizada.getTituloCategoria());
        existente.setAutorCategoria(categoriaActualizada.getAutorCategoria());
        existente.setDescripcionCategoria(categoriaActualizada.getDescripcionCategoria());

        return categoriaRepository.save(existente);
    }

    @Override
    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }
}
