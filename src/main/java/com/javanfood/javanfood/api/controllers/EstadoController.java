package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.EstadoResponse;
import com.javanfood.javanfood.api.dto.request.EstadoRequest;
import com.javanfood.javanfood.api.mapper.estadoMapper.EstadoRequestMapper;
import com.javanfood.javanfood.api.mapper.estadoMapper.EstadoResponseMapper;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.service.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;
    private final EstadoResponseMapper estadoResponseMapper;
    private final EstadoRequestMapper estadoRequestMapper;

    @GetMapping
    public List<EstadoResponse> listar() {
        return estadoResponseMapper.toDtoCollection(estadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoResponse buscarPorId(@PathVariable Long estadoId) {
        return estadoResponseMapper.toDto(estadoService.buscarOuFalha(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoResponse adicionar(@RequestBody @Valid EstadoRequest estadoRequest) {
        Estado estado = estadoRequestMapper.toDomainObject(estadoRequest);
        return estadoResponseMapper.toDto(estadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoResponse atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoRequest estadoRequest) {
        return estadoResponseMapper.toDto(estadoService.atualizar(estadoId, estadoRequest));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }

}
