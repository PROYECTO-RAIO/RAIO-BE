package com.raio_be.raio_be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;
import com.raio_be.raio_be.exception.CategoriaNotFoundException;
import com.raio_be.raio_be.exception.MensajeOriginalNotFoundException;
import com.raio_be.raio_be.exception.MensajeReverberadoNotFoundException;
import com.raio_be.raio_be.mapper.MensajeReverberadoMapper;
import com.raio_be.raio_be.model.Categoria;
import com.raio_be.raio_be.model.MensajeOriginal;
import com.raio_be.raio_be.model.MensajeReverberado;
import com.raio_be.raio_be.repository.CategoriaRepository;
import com.raio_be.raio_be.repository.MensajeOriginalRepository;
import com.raio_be.raio_be.repository.MensajeReverberadoRepository;

@Service
public class MensajeReverberadoImpl implements MensajeReverberadoService {

  @Autowired
  private MensajeReverberadoRepository mensajeReverberadoRepository;
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private MensajeOriginalRepository mensajeOriginalRepository;

  private MensajeReverberado toEntity(MensajeReverberadoDTO dto) {
    MensajeOriginal mensajeOriginal = mensajeOriginalRepository.findById(dto.getMensajeOriginal())
        .orElseThrow(() -> new MensajeOriginalNotFoundException(dto.getMensajeOriginal()));

    Categoria categoria = categoriaRepository.findById(dto.getCategoria())
        .orElseThrow(() -> new CategoriaNotFoundException(dto.getCategoria()));

    return MensajeReverberado.builder()
        .id(dto.getId())
        .asunto(dto.getAsunto())
        .autor(dto.getAutor())
        .cuerpo(dto.getCuerpo())
        .adjunto(dto.getAdjunto())
        .timestamp(dto.getTimestamp())
        .mensajeOriginal(mensajeOriginal)
        .categoria(categoria)
        .build();
  }

  @Override
  public MensajeReverberadoDTO createMensajeReverberado(MensajeReverberadoDTO mensajeReverberadoDTO) {
    MensajeReverberado mensajeReverberado = toEntity(mensajeReverberadoDTO);
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
  public MensajeReverberadoDTO updateMensajeReverberado(Integer id, MensajeReverberadoDTO mensajeReverberadoDTO) {
    MensajeReverberado existingMensajeReverberado = mensajeReverberadoRepository.findById(id)
        .orElseThrow(() -> new MensajeReverberadoNotFoundException(id));
    MensajeOriginal mensajeOriginal = mensajeOriginalRepository.findById(mensajeReverberadoDTO.getMensajeOriginal())
        .orElseThrow(() -> new MensajeOriginalNotFoundException(mensajeReverberadoDTO.getMensajeOriginal()));

    Categoria categoria = categoriaRepository.findById(mensajeReverberadoDTO.getCategoria())
        .orElseThrow(() -> new CategoriaNotFoundException(mensajeReverberadoDTO.getCategoria()));

    existingMensajeReverberado.setAsunto(mensajeReverberadoDTO.getAsunto());
    existingMensajeReverberado.setAutor(mensajeReverberadoDTO.getAutor());
    existingMensajeReverberado.setCuerpo(mensajeReverberadoDTO.getCuerpo());
    existingMensajeReverberado.setAdjunto(mensajeReverberadoDTO.getAdjunto());
    existingMensajeReverberado.setTimestamp(mensajeReverberadoDTO.getTimestamp());
    existingMensajeReverberado.setMensajeOriginal(mensajeOriginal);
    existingMensajeReverberado.setCategoria(categoria);

    MensajeReverberado updatedMensajeReverberado = mensajeReverberadoRepository.save(existingMensajeReverberado);
    return MensajeReverberadoMapper.toDto(updatedMensajeReverberado);
  }

  @Override
  public void deleteMensajeReverberado(Integer id) {
    if (!mensajeReverberadoRepository.existsById(id)) {
      throw new MensajeReverberadoNotFoundException(id);
    }
    mensajeReverberadoRepository.deleteById(id);
  }

}
