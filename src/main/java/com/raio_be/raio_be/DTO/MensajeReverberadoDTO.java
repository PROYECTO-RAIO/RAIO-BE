package com.raio_be.raio_be.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensajeReverberadoDTO {

  private Integer id;

  private String asunto;

  private String autor;

  private String cuerpo;

  private String adjunto;

  private LocalDateTime timestamp;

  private Integer mensajeOriginal;

  private Integer categoria;
}
