package com.javanfood.javanfood.controlers;


import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Cozinha> listar() {
        System.out.println("LISTAR 1");
        return cozinhaRepository.listar();
    }

    @GetMapping(produces =  MediaType.APPLICATION_XML_VALUE)
    public List<Cozinha> listar2() {
        System.out.println("LISTAR 2");
        return cozinhaRepository.listar();
    }   
}
