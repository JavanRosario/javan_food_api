package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.CidadeNaoEncontradoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código: %d não pode ser removida, pois está em uso";

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	public Cidade buscaOuFalha(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(
						() -> new CidadeNaoEncontradoExeption(cidadeId));
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado = cadastroEstado.buscaOuFalha(estadoId);
		try {
			cidade.setEstado(estado);
			return cidadeRepository.save(cidade);
		} catch (EntidadeNaoEncontradaExeption e) {
			throw new NegocioExeption(String.format(MSG_CIDADE_EM_USO, null));
		}

	}

	public void excluir(Long cidadeId) {

		if (!cidadeRepository.existsById(cidadeId)) {
			throw new CidadeNaoEncontradoExeption(cidadeId);
		}

		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}
