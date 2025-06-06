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
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listar()
                .stream()
                .map(CategoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoriaDTO obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(CategoriaMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public CategoriaDTO guardar(@RequestBody CategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        Categoria guardada = categoriaService.guardar(categoria);
        return CategoriaMapper.toDto(guardada);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        Categoria actualizada = categoriaService.actualizar(id, CategoriaMapper.toEntity(dto));
        return actualizada != null ? CategoriaMapper.toDto(actualizada) : null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
