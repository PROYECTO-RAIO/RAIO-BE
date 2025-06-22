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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mensajes_reverberados")
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

  private String asunto;

  private String autor;

  private String cuerpo;

  private String adjunto;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime timestamp;

  @ManyToOne
  @JoinColumn(name = "mensaje_original", nullable = false)
  private MensajeOriginal mensajeOriginal;

  @ManyToOne
  @JoinColumn(name = "categoria", nullable = false)
  @JsonBackReference
  private Categoria categoria;
}
