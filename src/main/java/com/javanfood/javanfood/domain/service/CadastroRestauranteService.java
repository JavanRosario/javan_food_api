package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.repository.PagamentoRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_ENTIDADE_EM_USO = "Restaurante de código: %d não pode ser removida, pois está em uso";

	private static final String MSG_NAO_ENCONTRADO = "Não existe cadastro de Restaurante com código: %d";

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	public Restaurante buscarOuFalha(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExeption(String.format(MSG_NAO_ENCONTRADO, restauranteId)));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExeption(
						"Não existe cadastro de cozinha com código %d".formatted(cozinhaId)));

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public void excluir(Long restauranteId) {
		
		if (!restauranteRepository.existsById(restauranteId)) {
			throw new EntidadeNaoEncontradaExeption(String.format(MSG_NAO_ENCONTRADO, restauranteId));
		}

		try {
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(MSG_ENTIDADE_EM_USO, restauranteId));
		}
	}
}

