package com.javanfood.javanfood.api.controler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javanfood.javanfood.domain.exeption.CozinhaNaoEncontradoExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.exeption.ValidacaoExeption;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.service.CadastroRestauranteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteControler {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private SmartValidator validator;


	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAllWithCozinhaFormaPagamentoList();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante listarId(@PathVariable Long restauranteId) {
		return cadastroRestauranteService.buscarOuFalha(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return cadastroRestauranteService.salvar(restaurante);
		} catch (CozinhaNaoEncontradoExeption e) {
			throw new NegocioExeption(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody @Valid Restaurante restaurante) {

		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalha(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
				"dataCadastro", "produtos");

		try {
			return cadastroRestauranteService.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradoExeption e) {
			throw new NegocioExeption(e.getMessage());
		}

	}

	@DeleteMapping
	public void deletar(Long restauranteid) {
		cadastroRestauranteService.excluir(restauranteid);
	}

	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(
			@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos,
			HttpServletRequest request) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalha(restauranteId);

		merge(campos, restauranteAtual, request);

		valide(restauranteAtual, "restaurante");

		return atualizar(restauranteId, restauranteAtual);
	}

	private void valide(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, result);

		if (result.hasErrors()) {
			throw new ValidacaoExeption(result);
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		
		
		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable causaRaiz = e.getCause();
			throw new HttpMessageNotReadableException(e.getMessage(), causaRaiz, httpRequest);
		}
	}
}
