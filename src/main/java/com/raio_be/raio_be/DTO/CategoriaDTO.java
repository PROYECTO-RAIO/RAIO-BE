package com.raio_be.raio_be.DTO;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoriaDTO {
    private Long id;
    private int mensajeReverberado;
    private String tituloCategoria;
    private String descripcionCategoria;
    private String autorCategoria;
    private String autorEmailCategoria;
    private String frecuenciaCategoria;
    private String totalLimitado;
    private String totalReverberaciones;
    private boolean estadoDeActividad;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String listaCorreoUrl;
    private String archivoUrl;
    private boolean demora;
    private String periodoRetraso;
}
