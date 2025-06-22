package com.raio_be.raio_be.mapper;

import org.springframework.stereotype.Component;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.model.MensajeOriginal;
import com.raio_be.raio_be.model.MensajeReverberado;
import com.raio_be.raio_be.repository.CategoriaRepository;
import com.raio_be.raio_be.repository.MensajeOriginalRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MensajeReverberadoMapper {

  private final MensajeOriginalRepository mensajeOriginalRepository;
  private final CategoriaRepository categoriaRepository;


  public static  MensajeReverberadoDTO toDto(MensajeReverberado mensajeReverberado) {
    return MensajeReverberadoDTO.builder()
        .id(mensajeReverberado.getId())
        .asunto(mensajeReverberado.getAsunto())
        .autor(mensajeReverberado.getAutor())
        .cuerpo(mensajeReverberado.getCuerpo())
        .adjunto(mensajeReverberado.getAdjunto())
        .timestamp(mensajeReverberado.getTimestamp())
        .mensajeOriginal(mensajeReverberado.getMensajeOriginal().getId())
        .categoria((mensajeReverberado.getCategoria().getId()))
        .build();
  }

   public MensajeReverberado toEntity(MensajeReverberadoDTO dto) {
    MensajeOriginal mensajeOriginal = mensajeOriginalRepository.findById(dto.getMensajeOriginal())
        .orElseThrow(() -> new RuntimeException("Mensaje original no encontrado"));

    Categoria categoria = categoriaRepository.findById(dto.getCategoria())
        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

    return MensajeReverberado.builder()
        .id(dto.getId())
        .asunto(dto.getAsunto())
        .autor(dto.getAutor())
        .cuerpo(dto.getCuerpo())
        .adjunto(dto.getAdjunto())
        .timestamp(dto.getTimestamp())
        .mensajeOriginal(mensajeOriginal)
        .categoria(categoria)
        .build();
  }
}
