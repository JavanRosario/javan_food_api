package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.api.repository.CidadeRepository;
import com.javanfood.javanfood.api.repository.EnderecoRespository;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EnderecoRespository enderecoRespository;

    public Cidade salvar(Cidade cidade) {
        Long enderecoId = cidade.getEndereco().getId();
        Endereco endereco = enderecoRespository.findById(enderecoId);

        if (endereco == null) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de endereço com código: " + enderecoId);
        }

        cidade.setEndereco(endereco);
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
