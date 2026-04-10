package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.SenhaUsuarioRequest;
import com.javanfood.javanfood.api.dto.request.UsuarioRequest;
import com.javanfood.javanfood.api.dto.request.UsuarioSemSenhaRequest;
import com.javanfood.javanfood.api.dto.response.UsuarioResponse;
import com.javanfood.javanfood.api.dto.response.UsuarioSemSenhaResponse;
import com.javanfood.javanfood.api.mapper.usuarioMapper.UsuarioRequestMapper;
import com.javanfood.javanfood.api.mapper.usuarioMapper.UsuarioResponseMapper;
import com.javanfood.javanfood.domain.model.Usuario;
import com.javanfood.javanfood.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioRequestMapper usuarioRequestMapper;
    private final UsuarioResponseMapper usuarioResponseMapper;
    private final UsuarioService usuarioService;


    @GetMapping
    public List<UsuarioSemSenhaResponse> listar() {
        return usuarioResponseMapper.usuarioSemSenhatoDtoCollection(usuarioService.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioSemSenhaResponse buscarPorId(@PathVariable Long usuarioId) {
        return usuarioResponseMapper.usuarioSemSenhaDto(usuarioService.buscarOuFalha(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse adicionar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequestMapper.toDomainObject(usuarioRequest);
        return usuarioResponseMapper.usuarioComSenhaDto(usuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioSemSenhaResponse atualizarSemSenha(@RequestBody @Valid UsuarioSemSenhaRequest usuarioSemSenhaRequest, @PathVariable Long usuarioId) {
        return usuarioResponseMapper.usuarioSemSenhaDto(usuarioService.atualizar(usuarioId, usuarioSemSenhaRequest));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void mudarSenha(@PathVariable Long usuarioId,
                           @RequestBody @Valid SenhaUsuarioRequest request) {
        usuarioService.mudarSenha(usuarioId, request.getSenhaAtual(), request.getNovaSenha());
    }

}
