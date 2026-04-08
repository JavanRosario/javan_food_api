package com.javanfood.javanfood.api.mapper.usuarioMapper;

import com.javanfood.javanfood.api.dto.request.UsuarioRequest;
import com.javanfood.javanfood.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioRequestMapper {
    Usuario toDomainObject(UsuarioRequest usuarioRequest);

    void updateEntityFromDto(Usuario usuario, @MappingTarget UsuarioRequest usuarioRequest);

}
