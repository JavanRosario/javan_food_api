package com.javanfood.javanfood.tests.formapagamento;

import com.javanfood.javanfood.JavanfoodApplication;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.infraistructure.repository.PagamentoRepositoryJpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaPagamentoMain {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(JavanfoodApplication.class).web(WebApplicationType.NONE)
				.run(args);

		PagamentoRepositoryJpa repositoryJpa = app.getBean(PagamentoRepositoryJpa.class);

		FormaPagamento formaPagamento = repositoryJpa.findById(1L);

        System.out.println("====================================");
        System.out.println(formaPagamento.getDescricao());
	}
}
