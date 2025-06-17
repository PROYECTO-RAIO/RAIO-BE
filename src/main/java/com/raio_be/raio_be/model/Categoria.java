package com.raio_be.raio_be.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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

    @Column(name = "id_mensaje_reverberado", nullable = false)
    private int mensajeReverberado;

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

    @Min(value = 1, message = "Debe ser al menos 1")
    private String totalReverberaciones;

    @NotNull(message = "El estado de actividad es obligatorio")
    private boolean estadoDeActividad;

    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    private LocalDate fechaInicio;

    @FutureOrPresent(message = "La fecha final no puede ser en el pasado")
    private LocalDate fechaFinal;

    @URL(message = "Debe ser una URL válida")
    @Pattern(regexp = "^(https?|ftp)://.*$", message = "URL inválida")
    private String listaCorreoUrl;

    @URL(message = "Debe ser una URL válida")
    @Pattern(regexp = "^(https?|ftp)://.*$", message = "URL inválida")
    private String archivoUrl;

    private boolean demora;

    @Column
    private String periodoRetraso;
}
