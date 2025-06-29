package com.raio_be.raio_be.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoriaDTO {
    private Long id;
    private List<MensajeReverberadoDTO> mensajesReverberados; 

    @NotBlank(message = "El título de la categoría es obligatorio")
    @Size(max = 50, message = "El título no puede tener más de 100 caracteres")
    private String tituloCategoria;

    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    private String descripcionCategoria;

    @Size(max = 100, message = "El autor no puede tener más de 100 caracteres")
    private String autorCategoria;

    @NotBlank(message = "El email del autor es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String autorEmailCategoria;

    private String frecuenciaCategoria;

    private String totalLimitado;

    private String totalReverberaciones;

    private boolean estadoDeActividad;

    private boolean temporalidad;

    private LocalDate fechaInicio;

    private LocalDate fechaFinal;

    @Email(message = "Debe ser un email válido")
    private String listaCorreo;

    @Pattern(regexp = "^(https?|ftp)://.*$", message = "Debe ser una URL válida")
    private String archivoUrl;

    private boolean demora;

    private Integer periodoRetraso;
}
