package com.raio_be.raio_be.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name = "mensajes_reverberados")
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<MensajeReverberado> mensajesReverberados = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String tituloCategoria;

    private String descripcionCategoria;

    private String autorCategoria;

    @Column(nullable = false)
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
