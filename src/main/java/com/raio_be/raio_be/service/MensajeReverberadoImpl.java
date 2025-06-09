package com.raio_be.raio_be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.model.MensajeReverberado;
import com.raio_be.raio_be.repository.MensajeReverberadoRepository;

@Service
public class MensajeReverberadoImpl implements MensajeReverberadoService {

  @Autowired
  private MensajeReverberadoRepository mensajeReverberadoRepository;

  private MensajeReverberadoDTO toDto(MensajeReverberado mensajeReverberado) {
    return MensajeReverberadoDTO.builder().id(mensajeReverberado.getId()).asunto(mensajeReverberado.getAsunto())
        .autor(mensajeReverberado.getAutor()).cuerpo(mensajeReverberado.getCuerpo())
        .adjunto(mensajeReverberado.getAdjunto()).timestamp(mensajeReverberado.getTimestamp())
        .mensajeOriginal(mensajeReverberado.getMensajeOriginal()).categoria(mensajeReverberado.getCategoria()).build();
  }

  private MensajeReverberado toEntity(MensajeReverberadoDTO dto) {
    return MensajeReverberado.builder().id(dto.getId()).asunto(dto.getAsunto()).autor(dto.getAutor())
        .cuerpo(dto.getCuerpo())
        .adjunto(dto.getAdjunto()).mensajeOriginal(dto.getMensajeOriginal())
        .categoria(dto.getCategoria()).build();
  }

  @Override
  public MensajeReverberadoDTO createMensajeReverberado(MensajeReverberadoDTO mensajeReverberadoDTO) {
    MensajeReverberado mensajeReverberado = toEntity(mensajeReverberadoDTO);
    MensajeReverberado savedMensajeReverberado = mensajeReverberadoRepository.save(mensajeReverberado);
    return toDto(savedMensajeReverberado);
  }

  @Override
  public List<MensajeReverberadoDTO> getAllMensajesReverberados() {
    return mensajeReverberadoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
  }

  @Override
  public MensajeReverberadoDTO getMensajeReverberadoById(Integer id) {
    MensajeReverberado mensajeReverberado = mensajeReverberadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
    return toDto(mensajeReverberado);
  }

  @Override
  public MensajeReverberadoDTO updateMensajeReverberado(Integer id, MensajeReverberadoDTO mensajeReverberadoDTO) {
    MensajeReverberado existingMensajeReverberado = mensajeReverberadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));

    existingMensajeReverberado.setAsunto(mensajeReverberadoDTO.getAsunto());
    existingMensajeReverberado.setAutor(mensajeReverberadoDTO.getAutor());
    existingMensajeReverberado.setCuerpo(mensajeReverberadoDTO.getCuerpo());
    existingMensajeReverberado.setAdjunto(mensajeReverberadoDTO.getAdjunto());
    existingMensajeReverberado.setTimestamp(mensajeReverberadoDTO.getTimestamp());
    existingMensajeReverberado.setMensajeOriginal(mensajeReverberadoDTO.getMensajeOriginal());
    existingMensajeReverberado.setCategoria(mensajeReverberadoDTO.getCategoria());

    MensajeReverberado updatedMensajeReverberado = mensajeReverberadoRepository.save(existingMensajeReverberado);
    return toDto(updatedMensajeReverberado);
  }

  @Override
  public void deleteMensajeReverberado(Integer id) {
    if (!mensajeReverberadoRepository.existsById(id)) {
      throw new RuntimeException("Mensaje no encontrado");
    }
    mensajeReverberadoRepository.deleteById(id);
  }

}
