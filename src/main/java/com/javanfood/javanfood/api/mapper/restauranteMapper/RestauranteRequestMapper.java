package com.javanfood.javanfood.api.mapper.restauranteMapper;

import com.javanfood.javanfood.api.dto.request.CidadeIdRequest;
import com.javanfood.javanfood.api.dto.request.CozinhaIdRequest;
import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestauranteRequestMapper {


    Restaurante toDomainObject(RestauranteRequest restauranteRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "formasPagamento", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    @Mapping(target = "endereco.cidade", ignore = true)
    void updateEntityFromDto(RestauranteRequest restauranteRequest, @MappingTarget Restaurante restaurante);

    @Mapping(target = "nome", ignore = true)
    Cozinha toCozinhaDomain(CozinhaIdRequest cozinhaIdRequest);


}
