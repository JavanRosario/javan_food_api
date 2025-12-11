package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.api.repository.CidadeRepository;
import com.javanfood.javanfood.api.repository.EstadoRespository;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRespository estadoRespository;

    public Cidade salvar(Cidade cidade) {
        Long enderecoId = cidade.getEstado().getId();
        Estado estado = estadoRespository.findById(enderecoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de endereço com código: " + enderecoId);
        }

        cidade.setEstado(estado);
        return cidadeRepository.adicionar(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.delete(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de Cidade com esse código: " + cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeNaoEncontradaExeption("Cidade de código: " + cidadeId + " não pode ser removida. Está em uso.");
        }
    }
}
