package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.GrupoResponse;
import com.javanfood.javanfood.api.mapper.grupo.GrupoResponseMapper;
import com.javanfood.javanfood.domain.model.Usuario;
import com.javanfood.javanfood.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
@AllArgsConstructor
public class UsuarioGrupoController {

    private final UsuarioService usuarioService;
    private final GrupoResponseMapper grupoResponseMapper;


    @GetMapping
    public List<GrupoResponse> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalha(usuarioId);
        return grupoResponseMapper.toDtoCollection(usuario.getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }

}
