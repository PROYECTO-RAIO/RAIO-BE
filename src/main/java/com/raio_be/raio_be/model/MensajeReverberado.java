package com.raio_be.raio_be.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
  private String asunto;

  @Column(nullable = false)
  private String autor;

  @Column(nullable = false)
  private String cuerpo;

  @Column(nullable = false)
  private String adjunto;

  @Column(nullable = false,updatable = false)
  @CreationTimestamp
  private LocalDateTime timestamp;

  @Column
  @JoinColumn(name = "id_mensaje_original", nullable = false)
  private Integer mensajeOriginal;

  @Column
  @JoinColumn(name = "id_categoria", nullable = false)
  private Integer categoria;
}
