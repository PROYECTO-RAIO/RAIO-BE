package com.raio_be.raio_be.service;

import java.util.List;

import com.raio_be.raio_be.DTO.MensajeReverberadoDTO;

public interface MensajeReverberadoService {

  MensajeReverberadoDTO createMensajeReverberado(MensajeReverberadoDTO mensajeReverberadoDTO);

  List<MensajeReverberadoDTO> getAllMensajesReverberados();

  MensajeReverberadoDTO getMensajeReverberadoById(Integer id);

  MensajeReverberadoDTO updateMensajeReverberado(Integer id, MensajeReverberadoDTO mensajeReverberadoDTO);

  void deleteMensajeReverberado(Integer id);
}
