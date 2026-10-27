package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.RestauranteRequest;
import com.javanfood.javanfood.api.dto.response.RestauranteResponse;
import com.javanfood.javanfood.api.mapper.restaurante.RestauranteRequestMapper;
import com.javanfood.javanfood.api.mapper.restaurante.RestauranteResponseMapper;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.projection.RestauranteNomeProjection;
import com.javanfood.javanfood.domain.repository.projection.RestauranteResumidoProjection;
import com.javanfood.javanfood.domain.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@RequiredArgsConstructor
@Tag(name = "Restaurantes", description = "Endpoints para gerenciamento de restaurantes")
public class RestauranteController {

    private final String menssagemGet = """
             {
                    "id": 1,
                    "nome": "Trattoria Del Nonno",
                    "taxaFrete": 8.90,
                    "cozinha": {
                        "id": 1,
                        "nome": "Italiana"
                    },
                    "ativo": true,
                    "aberto": true,
                    "endereco": {
                        "cep": "01308-000",
                        "logradouro": "Rua Augusta",
                        "numero": "1420",
                        "complemento": "Sobrado",
                        "bairro": "Jardins",
                        "cidade": {
                            "id": 3,
                            "nome": "São Paulo",
                            "estado": "São Paulo"
                        }
                    }
                }
            """;

    private final RestauranteService restauranteService;
    private final RestauranteResponseMapper restauranteResponseMapper;
    private final RestauranteRequestMapper restauranteRequestMapper;

    @GetMapping
    @Operation(summary = "Trás o resultado de restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!" , content = @Content(mediaType = "application/json", examples = @ExampleObject(value = menssagemGet))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<RestauranteResponse> listar() {
        return restauranteResponseMapper.toDtoCollection(restauranteService.listar());
    }

    @GetMapping(params = "projecao=resumo")
    public List<RestauranteResumidoProjection> listarResumido() {
        return restauranteService.listarResumido();
    }

    @GetMapping(params = "projecao=nome")
    public List<RestauranteNomeProjection> listarNome() {
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
