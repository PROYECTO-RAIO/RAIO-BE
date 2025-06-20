package com.raio_be.raio_be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raio_be.raio_be.exception.MensajeOriginalNotFoundException;
import com.raio_be.raio_be.model.MensajeOriginal;
import com.raio_be.raio_be.repository.CategoriaRepository;
import com.raio_be.raio_be.repository.MensajeOriginalRepository;

@Service
public class MensajeOriginalServiceImpl implements MensajeOriginalService {

    @Autowired
    private final MensajeOriginalRepository mensajeOriginalRepository;

    public MensajeOriginalServiceImpl(CategoriaRepository categoriaRepository,
            MensajeOriginalRepository mensajeOriginalRepository) {
        this.mensajeOriginalRepository = mensajeOriginalRepository;
    }

    @Override
    public List<MensajeOriginal> getAllMensajeOriginal() {
        return mensajeOriginalRepository.findAll();
    }

    @Override
    public Optional<MensajeOriginal> getMensajeOriginalById(Long id) {
        return mensajeOriginalRepository.findById(id);
    }

    @Override
    public MensajeOriginal saveMensajeOriginal(MensajeOriginal mensajeOriginal) {
        return mensajeOriginalRepository.save(mensajeOriginal);
    }

    @Override
    public MensajeOriginal updateMensajeOriginal(Long id, MensajeOriginal mensajeOriginal) {
        if (!mensajeOriginalRepository.existsById(id)) {
            throw new MensajeOriginalNotFoundException(id);
        }
        mensajeOriginal.setId(id);
        return mensajeOriginalRepository.save(mensajeOriginal);
    }

    @Override
    public void deleteMensajeOriginal(Long id) {
        if (!mensajeOriginalRepository.existsById(id)) {
            throw new MensajeOriginalNotFoundException(id);
        }
        mensajeOriginalRepository.deleteById(id);
    }
}
