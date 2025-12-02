package com.javanfood.javanfood.cozinha;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryJpa cadastroCozinha = app.getBean(CozinhaRepositoryJpa.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("testando");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("japonesa");

        Cozinha adicionar = cadastroCozinha.adicionar(cozinha1);
        Cozinha adicionar1 = cadastroCozinha.adicionar(cozinha2);


        System.out.println(adicionar.getId() + adicionar.getNome());


    }
}
