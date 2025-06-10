package com.raio_be.raio_be.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raio_be.raio_be.DTO.MensajeOriginalDTO;
import com.raio_be.raio_be.mapper.MensajeOriginalMapper;
import com.raio_be.raio_be.model.MensajeOriginal;
import com.raio_be.raio_be.service.MensajeOriginalService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/mensajes-originales")
public class MensajeOriginalController {

    @Autowired
    private MensajeOriginalService mensajeOriginalService;

    @GetMapping
    public List<MensajeOriginalDTO> getAllMensajeOriginal() {
        return mensajeOriginalService.getAllMensajeOriginal()
                .stream()
                .map(MensajeOriginalMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public MensajeOriginalDTO getMensajeOriginalById(@PathVariable Long id) {
        return mensajeOriginalService.getMensajeOriginalById(id)
                .map(MensajeOriginalMapper::toDto)
                .orElse(null);
    }

    @PostMapping()
    public MensajeOriginalDTO saveMensajeOriginal(@RequestBody MensajeOriginalDTO dto) {
        MensajeOriginal mensajeOriginal = MensajeOriginalMapper.toEntity(dto);
        MensajeOriginal save = mensajeOriginalService.saveMensajeOriginal(mensajeOriginal);
         return MensajeOriginalMapper.toDto(save);
    }

    @PutMapping("/{id}")
    public MensajeOriginalDTO updateMensajeOriginal(@PathVariable Long id, @RequestBody MensajeOriginalDTO dto) {
        MensajeOriginal update = mensajeOriginalService.updateMensajeOriginal(id, MensajeOriginalMapper.toEntity(dto));
        return update != null ? MensajeOriginalMapper.toDto(update) : null;
        
    }
    
    @DeleteMapping("/{id}")
    public void deleteMensajeOriginal(@PathVariable Long id) {
        mensajeOriginalService.deleteMensajeOriginal(id);
    }
    
}
