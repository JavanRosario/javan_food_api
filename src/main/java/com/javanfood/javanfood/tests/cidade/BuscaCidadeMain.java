package com.javanfood.javanfood.tests.cidade;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.infraistructure.repository.CidadeRepositoryJpa;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import com.javanfood.javanfood.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCidadeMain {
    public static void main(String[] args) {
        ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
                .run(args);

        CidadeRepositoryJpa bean = app.getBean(CidadeRepositoryJpa.class);

        Cidade cidade = bean.findById(1L);

        System.out.println("====================================");
        System.out.println(cidade.getNome()+" " + cidade.getEndereco().getNome());
    }
}
