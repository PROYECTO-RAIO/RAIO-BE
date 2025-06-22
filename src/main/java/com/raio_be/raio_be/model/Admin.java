package com.raio_be.raio_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Integer id;

  @Column(nullable = false)
  @NotBlank(message = "El nombre de usuarie no puede estar vacío")
  private String nombreUsuarie;

  @Column(nullable = false)
  @NotBlank(message = "La contraseña no puede estar vacía")
  private String contraseña;

  @Column(nullable = false)
  @NotBlank(message = "El email no puede estar vacío")
  @Email(message = "Formato de email inválido")
  private String email;

}
