package com.raio_be.raio_be.mapper;

import com.raio_be.raio_be.DTO.CategoriaDTO;
import com.raio_be.raio_be.model.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO toDto(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .mensajesReverberados(
                        categoria.getMensajesReverberados().stream()
                                .map(MensajeReverberadoMapper::toDto)
                                .toList())
                .tituloCategoria(categoria.getTituloCategoria())
                .descripcionCategoria(categoria.getDescripcionCategoria())
                .autorCategoria(categoria.getAutorCategoria())
                .autorEmailCategoria(categoria.getAutorEmailCategoria())
                .frecuenciaCategoria(categoria.getFrecuenciaCategoria())
                .totalLimitado(categoria.getTotalLimitado())
                .totalReverberaciones(categoria.getTotalReverberaciones())
                .estadoDeActividad(categoria.isEstadoDeActividad())
                .temporalidad(categoria.isTemporalidad())
                .fechaInicio(categoria.getFechaInicio())
                .fechaFinal(categoria.getFechaFinal())
                .listaCorreo(categoria.getListaCorreo())
                .archivoUrl(categoria.getArchivoUrl())
                .demora(categoria.isDemora())
                .periodoRetraso(categoria.getPeriodoRetraso())
                .build();
    }

    public static Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .tituloCategoria(dto.getTituloCategoria())
                .descripcionCategoria(dto.getDescripcionCategoria())
                .autorCategoria(dto.getAutorCategoria())
                .autorEmailCategoria(dto.getAutorEmailCategoria())
                .frecuenciaCategoria(dto.getFrecuenciaCategoria())
                .totalLimitado(dto.getTotalLimitado())
                .totalReverberaciones(dto.getTotalReverberaciones())
                .estadoDeActividad(dto.isEstadoDeActividad())
                .temporalidad(dto.isTemporalidad())
                .fechaInicio(dto.getFechaInicio())
                .fechaFinal(dto.getFechaFinal())
                .listaCorreo(dto.getListaCorreo())
                .archivoUrl(dto.getArchivoUrl())
                .demora(dto.isDemora())
                .periodoRetraso(dto.getPeriodoRetraso())
                .build();
    }
}
