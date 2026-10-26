package com.javanfood.javanfood.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.dto.response.RestauranteNomeResponse;
import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.api.dto.response.RestauranteResumidoResponse;
import com.javanfood.javanfood.api.mapper.restaurante.RestauranteRequestMapper;
import com.javanfood.javanfood.api.mapper.restaurante.RestauranteResponseMapper;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.model.view.RestauranteView;
import com.javanfood.javanfood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService restauranteService;
    private final RestauranteResponseMapper restauranteResponseMapper;
    private final RestauranteRequestMapper restauranteRequestMapper;

    @GetMapping
    public List<RestauranteResponse> listar() {
        return restauranteResponseMapper.toDtoCollection(restauranteService.listar());
    }

    @JsonView(RestauranteView.class)
    @GetMapping(params = "projecao=resumo")
    public List<RestauranteResumidoResponse> listarResumido() {
        return restauranteService.listarResumido();
    }

    @GetMapping(params = "projecao=nome")
    public List<RestauranteNomeResponse> listarNome() {
        return restauranteService.listarPorNome();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteResponse buscarPorId(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return restauranteResponseMapper.toDto(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse adicionar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
        Restaurante restaurante = restauranteRequestMapper.toDomainObject(restauranteRequest);
        return restauranteResponseMapper.toDto(restauranteService.salvar(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public RestauranteResponse atualizar(@PathVariable Long restauranteId,
                                         @RequestBody @Valid RestauranteRequest restauranteRequest) {
        return restauranteResponseMapper.toDto(restauranteService.atualizar(restauranteId, restauranteRequest));
    }

    @DeleteMapping("/{restauranteId}")
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

    @PutMapping("/{restauranteId}/abrir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
        restauranteService.ativarRestaurantes(restaurantesIds);
    }

    @DeleteMapping("/desativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarMultiplos(@RequestBody List<Long> restaurantesIds) {
        restauranteService.inativarRestaurantes(restaurantesIds);
    }
}
