package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.CidadeRequest;
import com.javanfood.javanfood.api.dto.response.CidadeResponse;
import com.javanfood.javanfood.api.mapper.cidadeMapper.CidadeRequestMapper;
import com.javanfood.javanfood.api.mapper.cidadeMapper.CidadeResponseMapper;
import com.javanfood.javanfood.domain.exeption.EstadoNaoEncontradoExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.repository.CidadeRepository;
import com.javanfood.javanfood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CadastroCidadeService cadastroCidadeService;
    private final CidadeResponseMapper cidadeResponseMapper;
    private final CidadeRequestMapper cidadeRequestMapper;


    @GetMapping
    public List<CidadeResponse> listar() {
        return cidadeResponseMapper.toDtoCollection(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeResponse listarId(@PathVariable Long cidadeId) {
        return cidadeResponseMapper.toDto(cadastroCidadeService.buscaOuFalha(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeResponse adicionar(@RequestBody @Valid CidadeRequest cidadeRequest) {
        try {
            Cidade cidade = cidadeRequestMapper.toDomainObject(cidadeRequest);
            cidade = cadastroCidadeService.salvar(cidade);
            return cidadeResponseMapper.toDto(cidade);
        } catch (EstadoNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeResponse atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeRequest cidadeRequest) {
        try {
            Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(cidadeId);
            cidadeRequestMapper.updateEntityFromDto(cidadeRequest, cidadeAtual);
            return cidadeResponseMapper.toDto(cadastroCidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }


}
