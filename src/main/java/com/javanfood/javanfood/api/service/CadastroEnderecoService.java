package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.api.repository.EnderecoRespository;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEnderecoService {

    @Autowired
    EnderecoRespository enderecoRespository;

    public Endereco salvar(Endereco endereco) {
        return enderecoRespository.adicionar(endereco);
    }

    public void excluir(Long enderecoId) {
        try {
            enderecoRespository.delete(enderecoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de endereço com código: " + enderecoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExeption("Endereço com código: " + enderecoId + " não pode ser removida. Em uso.");
        }
    }
}
