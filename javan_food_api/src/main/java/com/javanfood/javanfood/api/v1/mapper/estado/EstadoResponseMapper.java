package com.javanfood.javanfood.api.v1.mapper.estado;

import com.javanfood.javanfood.api.v1.dto.response.EstadoResponse;
import com.javanfood.javanfood.domain.model.Estado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoResponseMapper {
    EstadoResponse toDto(Estado estado);

    List<EstadoResponse> toDtoCollection(List<Estado> estados);
}
