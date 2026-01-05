package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.CidadeRepository;
import com.javanfood.javanfood.domain.repository.EstadoRespository;

@Service
public class CadastroCidadeService {
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe cadastro de Cidade com esse código: %d";

	private static final String MSG_CIDADE_EM_USO = "Cidade de código: %d não pode ser removida, pois está em uso";

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	public Cidade buscaOuFalha(Long cidade_id) {
		return cidadeRepository.findById(cidade_id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExeption(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidade_id)));
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = cadastroEstado.buscaOuFalha(estadoId);

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {

		if (!cidadeRepository.existsById(cidadeId)) {
			throw new EntidadeNaoEncontradaExeption(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
		}

		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}
