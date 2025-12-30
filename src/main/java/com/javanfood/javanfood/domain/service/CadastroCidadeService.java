package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.repository.CidadeRepository;
import com.javanfood.javanfood.domain.repository.EstadoRespository;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.model.Restaurante;

import java.util.List;

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
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRespository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaExeption(
                        "Não existe cadastro de Estado com código: %d".formatted(estadoId))
                );

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExeption("Não existe cadastro de Cidade com esse código: " + cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeNaoEncontradaExeption("Cidade de código: " + cidadeId + " não pode ser removida. Está em uso.");
        }
    }
    
   
}
