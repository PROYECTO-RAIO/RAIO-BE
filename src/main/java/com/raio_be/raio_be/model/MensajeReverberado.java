package com.raio_be.raio_be.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mensaje_reverberado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensajeReverberado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Integer id;

  @Column(nullable = false)
  @NotBlank(message = "El asunto no puede estar vacío")
  @Size(min = 3, max = 100, message = "El asunto debe tener entre 3 y 100 caracteres")
  private String asunto;

  @Column(nullable = false)
  @NotBlank(message = "El autor no puede estar vacío")
  @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
  private String autor;

  @Column(nullable = false)
  @NotBlank(message = "El cuerpo no puede estar vacío")
  private String cuerpo;

  @Column(nullable = false)
  @NotBlank(message = "El adjunto no puede estar vacío")
  private String adjunto;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime timestamp;

  @ManyToOne
  @JoinColumn(name = "mensaje_original", nullable = false)
  @JsonBackReference(value = "mensajeOriginal-mensajesReverberados")
  @NotNull(message = "Debe especificar el ID del mensaje original")
  private MensajeOriginal mensajeOriginal;

 @ManyToOne
  @JoinColumn(name = "categoria", nullable = false)
  @NotNull(message = "Debe especificar una categoría")
  @JsonBackReference(value = "categoria-mensajesReverberados")
  private Categoria categoria;
}
