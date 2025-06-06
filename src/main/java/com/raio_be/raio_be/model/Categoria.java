package com.raio_be.raio_be.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_mensaje_reverberado", nullable = false)
    private int mensajeReverberado;

    @Column(nullable = false)
    private String tituloCategoria;

    @Column
    private String descripcionCategoria;

    @Column
    private String autorCategoria;

    @Column
    private String autorEmailCategoria;

    @Column
    private String frecuenciaCategoria;

    @Column
    private String totalLimitado;

    @Column
    private String totalReverberaciones;

    @Column
    private boolean estadoDeActividad;

    @Column
    private LocalDateTime fechaInicio;

    @Column
    private LocalDateTime fechaFinal;

    @Column
    private String listaCorreoUrl;

    @Column
    private String archivoUrl;

    @Column
    private boolean demora;

    @Column
    private String periodoRetraso;
}
