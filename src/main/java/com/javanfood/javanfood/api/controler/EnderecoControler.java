package com.javanfood.javanfood.api.controler;

import com.javanfood.javanfood.domain.model.Endereco;
import com.javanfood.javanfood.api.repository.EnderecoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControler {

    @Autowired
    private EnderecoRespository enderecoRespository;

    @GetMapping
    public List<Endereco> list() {
        return enderecoRespository.listar();
    }
}
