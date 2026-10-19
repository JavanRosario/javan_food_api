package com.javanfood.javanfood.api.mapper.restaurante;

import com.javanfood.javanfood.api.dto.response.RestauranteNomeResponse;
import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.api.dto.response.RestauranteResumidoResponse;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.model.Restaurante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteResponseMapper {

    RestauranteResponse toDto(Restaurante restaurante);

    List<RestauranteResponse> toDtoCollection(List<Restaurante> restaurantes);

    List<RestauranteResumidoResponse> toDtoCollectionResumo(List<Restaurante> restaurantes);

    List<RestauranteNomeResponse> toDtoCollectionNome(List<Restaurante> restaurantes);

    default String map(Estado estado) {
        return estado != null ? estado.getNome() : null;
    }
}

