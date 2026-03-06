package com.javanfood.javanfood.api.mapper.restauranteMapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.javanfood.javanfood.api.dto.RestauranteModel;
import com.javanfood.javanfood.domain.model.Restaurante;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {
	@Mapping(source = "nome", target = "teste")
	RestauranteModel toDto(Restaurante restaurante);

	List<RestauranteModel> toDtoCollection(List<Restaurante> restaurantes);


}

