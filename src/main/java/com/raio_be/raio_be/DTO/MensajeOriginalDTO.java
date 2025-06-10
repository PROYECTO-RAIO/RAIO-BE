package com.raio_be.raio_be.DTO;

import java.sql.Timestamp;

import com.raio_be.raio_be.model.MensajeReverberado;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MensajeOriginalDTO {
    private Long id;
    private String asuntoMensajeOriginal;
    private String autorMensajeOriginal;
    private String cuerpoMensajeOriginal;
    private String adjuntoMensajeOriginal;
    private Timestamp timestamp;
    private MensajeReverberado mensajeReverberado;
}
