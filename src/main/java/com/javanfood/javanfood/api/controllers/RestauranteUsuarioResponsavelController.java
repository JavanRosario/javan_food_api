package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.UsuarioSemSenhaResponse;
import com.javanfood.javanfood.api.mapper.usuario.UsuarioResponseMapper;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restaurantes/{restauranteId}/usuarios")
@RequiredArgsConstructor
public class RestauranteUsuarioResponsavelController {
    private final RestauranteService restauranteService;
    private final UsuarioResponseMapper usuarioResponseMapper;

    @GetMapping
    public List<UsuarioSemSenhaResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return usuarioResponseMapper.usuarioSemSenhaToDtoCollection(restaurante.getUsuariosResponsaveis());
    }
}
