package com.raio_be.raio_be.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginDTO {
    private String email;
    private String contraseña;
}