package com.javanfood.javanfood;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.javanfood.javanfood.domain.model.Cozinha;

@SpringBootTest
class CadastroCozinhaAplicationTests {

	@Test
	public void testarCadastrarCozinhaComSucesso() {
		Cozinha exemploCozinha = new Cozinha();
		exemploCozinha.setNome("Chinesa");
	}
}
