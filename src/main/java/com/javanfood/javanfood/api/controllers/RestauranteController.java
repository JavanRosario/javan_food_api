package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteRequestMapper;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteResponseMapper;
import com.javanfood.javanfood.domain.exeption.CozinhaNaoEncontradoExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.service.CadastroRestauranteService;
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
    private final CadastroRestauranteService cadastroRestauranteService;
    private final RestauranteResponseMapper restauranteResponseMapper;
    private final RestauranteRequestMapper restauranteRequestMapper;


    @GetMapping
    public List<RestauranteResponse> listar() {
        return restauranteResponseMapper.toDtoCollection(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteResponse listarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalha(restauranteId);
        return restauranteResponseMapper.toDto(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse adicionar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
        try {

            Restaurante restaurante = restauranteRequestMapper.toDomainObject(restauranteRequest);

            return restauranteResponseMapper.toDto(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteResponse atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteRequest restauranteRequest) {
        try {

            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalha(restauranteId);

            restauranteRequestMapper.updateEntityFromDto(restauranteRequest, restauranteAtual);

            return restauranteResponseMapper.toDto(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }

    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.excluir(restauranteId);
    }

}
