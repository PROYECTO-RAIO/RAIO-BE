package com.raio_be.raio_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
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
  @Size(min = 3, max = 10, message = "El nombre de usuarie debe tener entre 3 y 10 caracteres")
  private String nombreUsuarie;

  @Column(nullable = false)
  @NotBlank(message = "La contraseña no puede estar vacía")
  // @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
  private String contraseña;

  @Column(nullable = false)
  @NotBlank(message = "El email no puede estar vacío")
  @Email(message = "Formato de email inválido")
  private String email;

}
