package com.javanfood.javanfood.repository;

import com.javanfood.javanfood.domain.model.Endereco;
import com.javanfood.javanfood.domain.model.Permisao;

import java.util.List;

public interface EnderecoRespository {
    List<Endereco> listar();

    Endereco findById(Long id);

    Endereco adicionar(Endereco endereco);

    void delete(Endereco endereco);
}
