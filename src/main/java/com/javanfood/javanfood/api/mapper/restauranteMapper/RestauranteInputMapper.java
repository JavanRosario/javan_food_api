package com.javanfood.javanfood.api.mapper.restauranteMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.javanfood.javanfood.api.dto.input.CozinhaInput;
import com.javanfood.javanfood.api.dto.input.RestauranteInput;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;

@Mapper(componentModel = "spring")
public interface RestauranteInputMapper {


	Restaurante toDomainObject(RestauranteInput dtoInput);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "formasPagamento", ignore = true)
	@Mapping(target = "dataCadastro", ignore = true)
	@Mapping(target = "produtos", ignore = true)
	@Mapping(target = "cozinha", source = "cozinha")
	void updateEntityFromDto(RestauranteInput restauranteInput, @MappingTarget Restaurante restaurante);

	@Mapping(target = "nome", ignore = true)
	Cozinha toCozinhaDomain(CozinhaInput cozinhaInput);

}
