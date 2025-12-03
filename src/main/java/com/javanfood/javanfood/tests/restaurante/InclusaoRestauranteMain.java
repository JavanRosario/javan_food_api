package com.javanfood.javanfood.tests.restaurante;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import com.javanfood.javanfood.infraistructure.repository.RestauranteRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepositoryJpa bean = app.getBean(RestauranteRepositoryJpa.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("TESTANTO");


        Restaurante adicionar = bean.adicionar(restaurante);


        System.out.println(adicionar.getId() + adicionar.getNome());


    }
}
