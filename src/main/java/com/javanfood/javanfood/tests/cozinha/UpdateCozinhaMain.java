package com.javanfood.javanfood.tests.cozinha;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);
        
        
        
        CozinhaRepositoryJpa cozinhaRepositoryJpa = app.getBean(CozinhaRepositoryJpa.class);
        
        
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("updating");

        cozinhaRepositoryJpa.adicionar(cozinha);
    }
}
