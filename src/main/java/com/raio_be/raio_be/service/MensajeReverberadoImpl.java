package com.raio_be.raio_be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.exception.MensajeReverberadoNotFoundException;
import com.raio_be.raio_be.mapper.MensajeReverberadoMapper;

import com.raio_be.raio_be.model.MensajeReverberado;

import com.raio_be.raio_be.repository.MensajeReverberadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensajeReverberadoImpl implements MensajeReverberadoService {

  private final MensajeReverberadoRepository mensajeReverberadoRepository;
  private final MensajeReverberadoMapper mensajeReverberadoMapper;

  @Override
  public MensajeReverberadoDTO createMensajeReverberado(MensajeReverberadoDTO mensajeReverberadoDTO) {
    MensajeReverberado mensajeReverberado = mensajeReverberadoMapper.toEntity(mensajeReverberadoDTO);
    MensajeReverberado savedMensajeReverberado = mensajeReverberadoRepository.save(mensajeReverberado);
    return MensajeReverberadoMapper.toDto(savedMensajeReverberado);
  }

  @Override
  public List<MensajeReverberadoDTO> getAllMensajesReverberados() {
    return mensajeReverberadoRepository.findAll().stream().map(MensajeReverberadoMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public MensajeReverberadoDTO getMensajeReverberadoById(Integer id) {
    MensajeReverberado mensajeReverberado = mensajeReverberadoRepository.findById(id)
        .orElseThrow(() -> new MensajeReverberadoNotFoundException(id));
    return MensajeReverberadoMapper.toDto(mensajeReverberado);
  }

  @Override
  public MensajeReverberadoDTO updateMensajeReverberado(Integer id, MensajeReverberadoDTO dto) {
    MensajeReverberado existing = mensajeReverberadoRepository.findById(id)
        .orElseThrow(() -> new MensajeReverberadoNotFoundException(id));

    MensajeReverberado actualizado = mensajeReverberadoMapper.toEntity(dto);
    actualizado.setId(existing.getId());

    MensajeReverberado saved = mensajeReverberadoRepository.save(actualizado);
    return MensajeReverberadoMapper.toDto(saved);
  }

  @Override
  public void deleteMensajeReverberado(Integer id) {
    if (!mensajeReverberadoRepository.existsById(id)) {
      throw new MensajeReverberadoNotFoundException(id);
    }
    mensajeReverberadoRepository.deleteById(id);
  }

}
