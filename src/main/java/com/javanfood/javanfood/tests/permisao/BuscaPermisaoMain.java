package com.javanfood.javanfood.tests.permisao;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Permisao;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.infraistructure.repository.PermicaoRepositoryJpa;
import com.javanfood.javanfood.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaPermisaoMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        PermicaoRepositoryJpa bean = app.getBean(PermicaoRepositoryJpa.class);

        Permisao permisao = bean.findById(1L);

        System.out.println("====================================");
        System.out.println(permisao.getNome());
    }
}
