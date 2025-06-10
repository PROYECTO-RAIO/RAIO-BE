package com.raio_be.raio_be.mapper;

import com.raio_be.raio_be.DTO.MensajeOriginalDTO;
import com.raio_be.raio_be.model.MensajeOriginal;

public class MensajeOriginalMapper {
    
    public static MensajeOriginalDTO toDto(MensajeOriginal mensajeOriginal) {
        return MensajeOriginalDTO.builder()
                .id(mensajeOriginal.getId())
                .asuntoMensajeOriginal(mensajeOriginal.getAutorMensajeOriginal())
                .autorMensajeOriginal(mensajeOriginal.getAutorMensajeOriginal())
                .cuerpoMensajeOriginal(mensajeOriginal.getCuerpoMensajeOriginal())
                .adjuntoMensajeOriginal(mensajeOriginal.getAdjuntoMensajeOriginal())
                .timestamp(mensajeOriginal.getTimestamp())
                .mensajeReverberado(mensajeOriginal.getMensajeReverberado())
                .build();

    }
    public static MensajeOriginal toEntity(MensajeOriginalDTO dto) {
        return MensajeOriginal.builder()
            .id(dto.getId())
            .asuntoMensajeOriginal(dto.getAsuntoMensajeOriginal())
            .autorMensajeOriginal(dto.getAutorMensajeOriginal())
            .cuerpoMensajeOriginal(dto.getCuerpoMensajeOriginal())
            .adjuntoMensajeOriginal(dto.getAdjuntoMensajeOriginal())
            .timestamp(dto.getTimestamp())
            .mensajeReverberado(dto.getMensajeReverberado())
            .build();

    }
}


