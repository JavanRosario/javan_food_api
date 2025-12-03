package com.javanfood.javanfood.tests.endereco;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Endereco;
import com.javanfood.javanfood.infraistructure.repository.CozinhaRepositoryJpa;
import com.javanfood.javanfood.infraistructure.repository.EnderecoRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaEndecoMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
				.run(args);

		EnderecoRepositoryJpa bean = app.getBean(EnderecoRepositoryJpa.class);

		Endereco endereco = bean.findById(1L);

        System.out.println("====================================");
        System.out.println(endereco.getNome());
	}
}
