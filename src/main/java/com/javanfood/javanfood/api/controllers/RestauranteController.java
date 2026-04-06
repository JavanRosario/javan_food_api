package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteRequestMapper;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteResponseMapper;
import com.javanfood.javanfood.domain.exception.CidadeNaoEncontradoException;
import com.javanfood.javanfood.domain.exception.CozinhaNaoEncontradoException;
import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteService restauranteService;
    private final RestauranteResponseMapper restauranteResponseMapper;
    private final RestauranteRequestMapper restauranteRequestMapper;


    @GetMapping
    public List<RestauranteResponse> listar() {
        return restauranteResponseMapper.toDtoCollection(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteResponse listarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return restauranteResponseMapper.toDto(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse adicionar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
        try {
            Restaurante restaurante = restauranteRequestMapper.toDomainObject(restauranteRequest);
            return restauranteResponseMapper.toDto(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteResponse atualizar(@PathVariable Long restauranteId,
                                         @RequestBody @Valid RestauranteRequest restauranteRequest) {
        try {
            return restauranteResponseMapper.toDto(
                    restauranteService.atualizar(restauranteId, restauranteRequest)
            );
        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
        restauranteService.excluir(restauranteId);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long restauranteId) {
        restauranteService.desativar(restauranteId);
    }
}
