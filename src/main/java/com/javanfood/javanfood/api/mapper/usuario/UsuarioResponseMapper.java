package com.javanfood.javanfood.api.mapper.usuario;

import com.javanfood.javanfood.api.dto.response.UsuarioResponse;
import com.javanfood.javanfood.api.dto.response.UsuarioSemSenhaResponse;
import com.javanfood.javanfood.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioResponseMapper {
    UsuarioSemSenhaResponse usuarioSemSenhaDto(Usuario usuario);

    List<UsuarioSemSenhaResponse> usuarioSemSenhaToDtoCollection(Collection<Usuario> usuarios);

    UsuarioResponse usuarioComSenhaDto(Usuario usuario);


}
