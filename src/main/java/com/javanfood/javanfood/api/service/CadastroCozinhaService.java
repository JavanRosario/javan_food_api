package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.api.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNãoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.adicionar(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.delete(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNãoEncontradaExeption("Não existe cadastro de cozinha com código: " + cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExeption
                    ("Cozinha de código " + cozinhaId + " não pode ser removida. Está em uso");
        }

    }
}
