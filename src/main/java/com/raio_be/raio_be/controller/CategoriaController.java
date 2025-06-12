package com.raio_be.raio_be.controller;

import com.raio_be.raio_be.DTO.CategoriaDTO;
import com.raio_be.raio_be.mapper.CategoriaMapper;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return categoriaService.getCategoriaById(id)
                .map(CategoriaMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public CategoriaDTO saveCategoria(@RequestBody CategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        Categoria save = categoriaService.saveCategoria(categoria);
        return CategoriaMapper.toDto(save);
    }

    @PutMapping("/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        Categoria update = categoriaService.updateCategoria(id, CategoriaMapper.toEntity(dto));
        return update != null ? CategoriaMapper.toDto(update) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
    }
}
