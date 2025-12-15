package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.repository.EstadoRespository;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    EstadoRespository estadoRespository;

    public Estado salvar(Estado estado) {
        return estadoRespository.save(estado);
    }

    public void excluir(Long enderecoId) {
        try {
            estadoRespository.deleteById(enderecoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de endereço com código: " + enderecoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExeption("Endereço com código: " + enderecoId + " não pode ser removida. Em uso.");
        }
    }
}
