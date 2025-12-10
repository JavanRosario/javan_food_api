package com.javanfood.javanfood.api.service;

import com.javanfood.javanfood.api.repository.CozinhaRepository;
import com.javanfood.javanfood.api.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaExeption(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.adicionar(restaurante);
    }   
}

