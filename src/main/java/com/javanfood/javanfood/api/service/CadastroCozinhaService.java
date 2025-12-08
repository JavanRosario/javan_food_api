package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.api.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.adicionar(cozinha);
    }
}
