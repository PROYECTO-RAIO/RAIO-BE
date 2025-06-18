package com.raio_be.raio_be.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.raio_be.raio_be.model.MensajeReverberado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoriaDTO {
    private Long id;
    private List<MensajeReverberado> mensajesReverberados;
    @NotBlank
    private String tituloCategoria;
    private String descripcionCategoria;
    private String autorCategoria;
    @Email
    private String autorEmailCategoria;
    private String frecuenciaCategoria;
    private String totalLimitado;
    private String totalReverberaciones;
    private boolean estadoDeActividad;
    private boolean temporalidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String listaCorreo;
    @Email
    private String archivoUrl;
    private boolean demora;
    private Integer periodoRetraso;
}
