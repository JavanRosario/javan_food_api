package com.javanfood.javanfood.restaurante;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import com.javanfood.javanfood.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository cozinhaRepositoryJpa = app.getBean(RestauranteRepository.class);

        Restaurante restaurante = cozinhaRepositoryJpa.findById(1L);

        System.out.println("====================================");
        System.out.println(restaurante.getNome());
    }
}
