package com.javanfood.javanfood.cozinha;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepositoryJpa cadastroCozinha = app.getBean(CozinhaRepositoryJpa.class);

		Cozinha cozinha = cadastroCozinha.findById(1L);

        System.out.println("====================================");
        System.out.println(cozinha.getNome());
	}
}
