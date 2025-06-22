package com.raio_be.raio_be.controller;

import com.raio_be.raio_be.DTO.CategoriaDTO;
import com.raio_be.raio_be.exception.CategoriaNotFoundException;
import com.raio_be.raio_be.mapper.CategoriaMapper;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.service.CategoriaService;
import com.raio_be.raio_be.exception.CategoryHasReverbsException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.getAllCategorias()
                .stream()
                .map(CategoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoriaDTO getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoriaById(id)
        .orElseThrow(() -> new CategoriaNotFoundException(id));
    return CategoriaMapper.toDto(categoria);
    }

    @PostMapping
    public CategoriaDTO saveCategoria(@Valid @RequestBody CategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        Categoria save = categoriaService.saveCategoria(categoria);
        return CategoriaMapper.toDto(save);
    }

    @PutMapping("/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        Categoria update = categoriaService.updateCategoria(id, CategoriaMapper.toEntity(dto));
        return update != null ? CategoriaMapper.toDto(update) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
    try {categoriaService.deleteCategoria(id);
    }
    catch (DataIntegrityViolationException e) {
        throw new CategoryHasReverbsException("No se puede eliminar la categoría porque tiene reverberaciones asociadas.\nPuedes cambiar su estado a inactivo.");
    }
}
}

