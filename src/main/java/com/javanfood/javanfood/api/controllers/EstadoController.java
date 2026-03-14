package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.EstadoResponse;
import com.javanfood.javanfood.api.dto.request.EstadoRequest;
import com.javanfood.javanfood.api.mapper.estadoMapper.EstadoRequestMapper;
import com.javanfood.javanfood.api.mapper.estadoMapper.EstadoResponseMapper;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.EstadoRespository;
import com.javanfood.javanfood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

    private final EstadoRespository estadoRespository;
    private final CadastroEstadoService cadastroEstadoService;
    private final EstadoResponseMapper estadoResponseMapper;
    private final EstadoRequestMapper estadoRequestMapper;

    @GetMapping
    public List<EstadoResponse> listar() {
        return estadoResponseMapper.toDtoCollection(estadoRespository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoResponse listarid(@PathVariable Long estadoId) {
        return estadoResponseMapper.toDto(cadastroEstadoService.buscaOuFalha(estadoId));
    }

    @PostMapping
    public EstadoResponse adicionar(@RequestBody @Valid EstadoRequest estadoRequest) {
        Estado estado = estadoRequestMapper.toDomainObject(estadoRequest);
        return estadoResponseMapper.toDto(cadastroEstadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoRequest estadoRequest) {
        Estado estadoAtual = cadastroEstadoService.buscaOuFalha(estadoId);
        estadoRequestMapper.updateEntityFromDTO(estadoRequest, estadoAtual);
        return cadastroEstadoService.salvar(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }

}
