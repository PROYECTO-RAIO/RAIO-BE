package com.raio_be.raio_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.service.MensajeReverberadoService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mensajes-reverberados")
public class MensajeReverberadoController {

    @Autowired
    private MensajeReverberadoService mensajeReverberadoService;

    @PostMapping
    public ResponseEntity<MensajeReverberadoDTO> createMensajeReverberado(
            @Valid @RequestBody MensajeReverberadoDTO dto) {
        MensajeReverberadoDTO created = mensajeReverberadoService.createMensajeReverberado(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<MensajeReverberadoDTO>> getAllMensajesReverberados() {
        List<MensajeReverberadoDTO> mensajes = mensajeReverberadoService.getAllMensajesReverberados();
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeReverberadoDTO> getMensajeReverberadoById(@PathVariable Integer id) {
        MensajeReverberadoDTO mensaje = mensajeReverberadoService.getMensajeReverberadoById(id);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeReverberadoDTO> updateMensajeReverberado(@PathVariable Integer id,
            @Valid @RequestBody MensajeReverberadoDTO dto) {
        MensajeReverberadoDTO updated = mensajeReverberadoService.updateMensajeReverberado(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMensajeReverberado(@PathVariable Integer id) {
        mensajeReverberadoService.deleteMensajeReverberado(id);
        return ResponseEntity.ok(Map.of("mensaje", "Reverberación con ID " + id + " eliminada correctamente"));
    }
}
