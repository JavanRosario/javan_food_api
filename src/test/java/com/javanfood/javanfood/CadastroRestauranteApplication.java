package com.javanfood.javanfood;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.service.CadastroRestauranteService;
import com.javanfood.javanfood.util.DataBaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteApplication {


	@LocalServerPort
	private int port;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private DataBaseCleaner baseCleaner;


	public void prepararDados() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("cozinha Teste");

		cozinhaRepository.save(cozinha);

		Restaurante restaurante = new Restaurante();
		restaurante.setNome("restaurante de teste");
		restaurante.setTaxaFrete(new BigDecimal(5.0));
		restaurante.setCozinha(cozinha);

		restauranteRepository.save(restaurante);

	}

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/restaurantes";
		RestAssured.port = port;
		baseCleaner.clearTables();
		prepararDados();
	}


	@Test
	public void deveRetornar200_QuandoConsultarRestaurante() {
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(200);
	}

	@Test
	public void deveReturnar201_QuandoCadastrarRestaurante() {
		RestAssured.given()
				.body("{\"nome\":\"teste\",\"taxaFrete\" : 5.0, \"cozinha\" : {\"id\": 1}}")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(201);
	}

	@Test
	public void deveLancarException_QuandoExcluirRestauranteInexistente() {
		Long restauranteId = -1L;

		assertThrows(EntidadeNaoEncontradaExeption.class, () -> {
			cadastroRestauranteService.excluir(restauranteId);
		});

	}

	@Test
	public void deveRetornarStatusDeRestauranteCorretos_QuandoConsultarRestauranteExistente() {
		RestAssured.given()
				.pathParam("restauranteId", 1)
				.body(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get("{restauranteId}")
				.then()
				.statusCode(HttpStatus.OK.value());

	}

	@Test
	public void deveConterRestaurantes_QuandoConsultar() {
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.body("", Matchers.hasSize(1));
	}
}
