package com.raio_be.raio_be.service;

import java.util.List;
import java.util.Optional;

import com.raio_be.raio_be.model.MensajeOriginal;

public interface MensajeOriginalService {

    List<MensajeOriginal> getAllMensajeOriginal();

    Optional<MensajeOriginal> getMensajeOriginalById(Long id);

    MensajeOriginal saveMensajeOriginal(MensajeOriginal mensajeOriginal);

    MensajeOriginal updateMensajeOriginal(Long id, MensajeOriginal mensajeOriginal);

    void deleteMensajeOriginal(Long id);
}