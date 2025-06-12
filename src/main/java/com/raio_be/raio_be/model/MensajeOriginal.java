package com.raio_be.raio_be.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mensajes_originales")
public class MensajeOriginal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String asuntoMensajeOriginal;
    @Column
    private String autorMensajeOriginal;
    @Column
    private String cuerpoMensajeOriginal;
    @Column
    private String adjuntoMensajeOriginal;
    @Column
    private Timestamp timestamp;
    @Column(name = "mensajesReverberados")
    @OneToMany(mappedBy = "mensajeOriginal", cascade = CascadeType.ALL)
    private List<MensajeReverberado> mensajesReverberados;

}
