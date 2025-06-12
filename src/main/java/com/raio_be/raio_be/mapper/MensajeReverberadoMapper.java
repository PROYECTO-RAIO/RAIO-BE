package com.raio_be.raio_be.mapper;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.model.MensajeReverberado;

public class MensajeReverberadoMapper {
  public static MensajeReverberadoDTO toDto(MensajeReverberado mensajeReverberado) {
    return MensajeReverberadoDTO.builder().id(mensajeReverberado.getId()).asunto(mensajeReverberado.getAsunto())
        .autor(mensajeReverberado.getAutor()).cuerpo(mensajeReverberado.getCuerpo())
        .adjunto(mensajeReverberado.getAdjunto()).timestamp(mensajeReverberado.getTimestamp())
        .mensajeOriginal(mensajeReverberado.getMensajeOriginal().getId())
        .categoria(mensajeReverberado.getCategoria().getId())
        .build();
  }
}
