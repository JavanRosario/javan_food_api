package com.javanfood.javanfood.restaurante;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.infraistructure.repository.RestauranteRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepositoryJpa bean = app.getBean(RestauranteRepositoryJpa.class);

        List<Restaurante> list = bean.listar();

        for (Restaurante c : list) {
            System.out.println(c.getNome()+ "-"+c.getTaxaFrete()+ "-"+c.getCozinha().getNome());
        }
    }
}
