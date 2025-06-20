package com.raio_be.raio_be.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private List<MensajeReverberado> mensajesReverberados;

    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String tituloCategoria;

    @Size(max = 1000)
    private String descripcionCategoria;

    @Size(max = 100)
    private String autorCategoria;

    @Email(message = "Debe ser un email válido")
    @Column(nullable = false)
    private String autorEmailCategoria;

    private String frecuenciaCategoria;

    private String totalLimitado;

    private String totalReverberaciones;

    @NotNull(message = "El estado de actividad es obligatorio")
    private boolean estadoDeActividad;

    private boolean temporalidad;

    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    private LocalDate fechaInicio;

    @FutureOrPresent(message = "La fecha final no puede ser en el pasado")
    private LocalDate fechaFinal;

    @Email(message = "Debe ser un email válido")
    private String listaCorreo;

    @URL(message = "Debe ser una URL válida")
    @Pattern(regexp = "^(https?|ftp)://.*$", message = "URL inválida")
    private String archivoUrl;

    private boolean demora;

    @Column
    private Integer periodoRetraso;
}
