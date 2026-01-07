package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EstadoNaoEncontradoExeption;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.EstadoRespository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ENTIDADE_EM_USO = "Estado de código: %d não pode ser removida, pois está em uso";


	@Autowired
	EstadoRespository estadoRespository;

	public Estado buscaOuFalha(Long estadoId) {
		return estadoRespository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoExeption(estadoId));
	}

	public Estado salvar(Estado estado) {
		return estadoRespository.save(estado);
	}

	public void excluir(Long estadoId) {

		if (!estadoRespository.existsById(estadoId)) {
			throw new EstadoNaoEncontradoExeption(estadoId);
		}

		try {
			estadoRespository.deleteById(estadoId);
			estadoRespository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(MSG_ENTIDADE_EM_USO, estadoId));
		}
	}
}
