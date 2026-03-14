package com.javanfood.javanfood.api.mapper.restauranteMapper;

import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.domain.model.Restaurante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteResponseMapper {

    RestauranteResponse toDto(Restaurante restaurante);

    List<RestauranteResponse> toDtoCollection(List<Restaurante> restaurantes);


}

