package com.javanfood.javanfood.api.mapper.estadoMapper;

import com.javanfood.javanfood.api.dto.response.EstadoResponse;
import com.javanfood.javanfood.domain.model.Estado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoResponseMapper {
    EstadoResponse toDto(Estado estado);

    List<EstadoResponse> toDtoCollection(List<Estado> estados);
}
