package com.raio_be.raio_be.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.raio_be.raio_be.model.MensajeReverberado;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoriaDTO {
    private Long id;
    private List<MensajeReverberado> mensajesReverberados;
    private String tituloCategoria;
    private String descripcionCategoria;
    private String autorCategoria;
    private String autorEmailCategoria;
    private String frecuenciaCategoria;
    private String totalLimitado;
    private String totalReverberaciones;
    private boolean estadoDeActividad;
    private boolean temporalidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String listaCorreo;
    private String archivoUrl;
    private boolean demora;
    private Integer periodoRetraso;
}
