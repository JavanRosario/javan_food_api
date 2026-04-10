package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.GrupoRequest;
import com.javanfood.javanfood.api.dto.response.GrupoResponse;
import com.javanfood.javanfood.api.mapper.grupoMapper.GrupoRequestMapper;
import com.javanfood.javanfood.api.mapper.grupoMapper.GrupoResponseMapper;
import com.javanfood.javanfood.domain.model.Grupo;
import com.javanfood.javanfood.domain.service.GrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;
    private final GrupoResponseMapper grupoResponseMapper;
    private final GrupoRequestMapper grupoRequestMapper;

    @GetMapping
    public List<GrupoResponse> listar() {
        return grupoResponseMapper.toDtoCollection(grupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoResponse buscarPorId(@PathVariable Long grupoId) {
        return grupoResponseMapper.toDto(grupoService.buscarOuFalha(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse adicionar(@RequestBody @Valid GrupoRequest grupoRequest) {
        Grupo grupo = grupoRequestMapper.toDomainObject(grupoRequest);
        return grupoResponseMapper.toDto(grupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoResponse atualizar(@RequestBody @Valid GrupoRequest grupoRequest, @PathVariable Long grupoId) {
        return grupoResponseMapper.toDto(grupoService.atualizar(grupoId, grupoRequest));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletar(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }


}
