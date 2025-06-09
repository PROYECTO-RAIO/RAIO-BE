package com.raio_be.raio_be.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminDTO {
  private Integer id;

  @NotBlank(message = "El nombre de usuarie no puede estar vacío")
  @Size(min = 3, max = 10, message = "El nombre de usuarie debe tener entre 3 y 10 caracteres")
  private String nombreUsuarie;

  @NotBlank(message = "La contraseña no puede estar vacía")
  @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
  private String contraseña;

  @NotBlank(message = "El email no puede estar vacío")
  @Email(message = "Formato de email inválido")
  private String email;
}
