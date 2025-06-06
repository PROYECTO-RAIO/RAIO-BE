package com.raio_be.raio_be.mapper;

import com.raio_be.raio_be.DTO.CategoriaDTO;
import com.raio_be.raio_be.model.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO toDto(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .mensajeReverberado(categoria.getMensajeReverberado())
                .tituloCategoria(categoria.getTituloCategoria())
                .descripcionCategoria(categoria.getDescripcionCategoria())
                .autorCategoria(categoria.getAutorCategoria())
                .autorEmailCategoria(categoria.getAutorEmailCategoria())
                .frecuenciaCategoria(categoria.getFrecuenciaCategoria())
                .totalLimitado(categoria.getTotalLimitado())
                .totalReverberaciones(categoria.getTotalReverberaciones())
                .estadoDeActividad(categoria.isEstadoDeActividad())
                .fechaInicio(categoria.getFechaInicio())
                .fechaFinal(categoria.getFechaFinal())
                .listaCorreoUrl(categoria.getListaCorreoUrl())
                .archivoUrl(categoria.getArchivoUrl())
                .demora(categoria.isDemora())
                .periodoRetraso(categoria.getPeriodoRetraso())
                .build();
    }

    public static Categoria toEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .mensajeReverberado(dto.getMensajeReverberado())
                .tituloCategoria(dto.getTituloCategoria())
                .descripcionCategoria(dto.getDescripcionCategoria())
                .autorCategoria(dto.getAutorCategoria())
                .autorEmailCategoria(dto.getAutorEmailCategoria())
                .frecuenciaCategoria(dto.getFrecuenciaCategoria())
                .totalLimitado(dto.getTotalLimitado())
                .totalReverberaciones(dto.getTotalReverberaciones())
                .estadoDeActividad(dto.isEstadoDeActividad())
                .fechaInicio(dto.getFechaInicio())
                .fechaFinal(dto.getFechaFinal())
                .listaCorreoUrl(dto.getListaCorreoUrl())
                .archivoUrl(dto.getArchivoUrl())
                .demora(dto.isDemora())
                .periodoRetraso(dto.getPeriodoRetraso())
                .build();
    }
}
