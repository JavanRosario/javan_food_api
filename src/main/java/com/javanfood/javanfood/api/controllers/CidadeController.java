package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.CidadeRequest;
import com.javanfood.javanfood.api.dto.response.CidadeResponse;
import com.javanfood.javanfood.api.mapper.cidadeMapper.CidadeRequestMapper;
import com.javanfood.javanfood.api.mapper.cidadeMapper.CidadeResponseMapper;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeResponseMapper cidadeResponseMapper;
    private final CidadeRequestMapper cidadeRequestMapper;


    @GetMapping
    public List<CidadeResponse> listar() {
        return cidadeResponseMapper.toDtoCollection(cidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public CidadeResponse buscarPorId(@PathVariable Long cidadeId) {
        return cidadeResponseMapper.toDto(cidadeService.buscarOuFalha(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeResponse adicionar(@RequestBody @Valid CidadeRequest cidadeRequest) {
        Cidade cidade = cidadeRequestMapper.toDomainObject(cidadeRequest);
        cidade = cidadeService.salvar(cidade);
        return cidadeResponseMapper.toDto(cidade);
    }

    @PutMapping("/{cidadeId}")
    public CidadeResponse atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeRequest cidadeRequest) {
        return cidadeResponseMapper.toDto(cidadeService.atualizar(cidadeId, cidadeRequest));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }


}
