package com.javanfood.javanfood.tests.cozinha;

import java.util.List;

import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryJpa cozinhaRepositoryJpa = app.getBean(CozinhaRepositoryJpa.class);

        List<Cozinha> cozinhas = cozinhaRepositoryJpa.listar();

        for (Cozinha c : cozinhas) {
            System.out.println(c.getNome());
        }
    }
}
