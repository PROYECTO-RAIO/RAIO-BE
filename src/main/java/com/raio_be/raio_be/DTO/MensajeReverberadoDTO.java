package com.raio_be.raio_be.DTO;

import java.time.LocalDateTime;

import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.model.MensajeOriginal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

  @NotBlank(message = "El asunto no puede estar vacío")
  @Size(min = 3, max = 100, message = "El asunto debe tener entre 3 y 100 caracteres")
  private String asunto;

  @NotBlank(message = "El autor no puede estar vacío")
  @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
  private String autor;

  @NotBlank(message = "El cuerpo no puede estar vacío")
  private String cuerpo;

  @NotBlank(message = "El adjunto no puede estar vacío")
  private String adjunto;

  private LocalDateTime timestamp;

  @NotNull(message = "Debe especificar el ID del mensaje original")
  private MensajeOriginal mensajeOriginal;

  @NotNull(message = "Debe especificar una categoría")
  private Categoria categoria;
}
