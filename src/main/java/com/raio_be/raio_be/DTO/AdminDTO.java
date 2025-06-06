package com.raio_be.raio_be.DTO;

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
  private String nombreUsuarie;
  private String contraseña;
  private String email;
}
