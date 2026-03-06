package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.RestauranteModel;
import com.javanfood.javanfood.api.dto.input.RestauranteInput;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteInputMapper;
import com.javanfood.javanfood.api.mapper.restauranteMapper.RestauranteMapper;
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
    private final RestauranteMapper restauranteMapper;
    private final RestauranteInputMapper restauranteInputMapper;


    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteMapper.toDtoCollection(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel listarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalha(restauranteId);
        return restauranteMapper.toDto(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {

            Restaurante restaurante = restauranteInputMapper.toDomainObject(restauranteInput);

            return restauranteMapper.toDto(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }
    
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {
        try {

            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalha(restauranteId);

            restauranteInputMapper.updateEntityFromDto(restauranteInput, restauranteAtual);

            return restauranteMapper.toDto(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradoExeption e) {
            throw new NegocioExeption(e.getMessage());
        }

    }

    @DeleteMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.excluir(restauranteId);
    }

//	@PatchMapping("/{restauranteId}")
//	public RestauranteModel atualizarParcial(
//			@PathVariable Long restauranteId,
//			@RequestBody Map<String, Object> campos,
//			HttpServletRequest request) {
//		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalha(restauranteId);
//
//		merge(campos, restauranteAtual, request);
//
//		valide(restauranteAtual, "restaurante");
//
//		return atualizar(restauranteId, restauranteAtual);
//	}

//	private void valide(Restaurante restaurante, String objectName) {
//		BeanPropertyBindingResult result = new BeanPropertyBindingResult(restaurante, objectName);
//		validator.validate(restaurante, result);
//
//		if (result.hasErrors()) {
//			throw new ValidacaoExeption(result);
//		}
//	}

//	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
//
//
//		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
//
//
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//
//			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//				field.setAccessible(true);
//
//				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//				ReflectionUtils.setField(field, restauranteDestino, novoValor);
//			});
//		} catch (IllegalArgumentException e) {
//			Throwable causaRaiz = e.getCause();
//			throw new HttpMessageNotReadableException(e.getMessage(), causaRaiz, httpRequest);
//		}
//	}


}
