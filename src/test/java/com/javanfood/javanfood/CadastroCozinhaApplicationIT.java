package com.javanfood.javanfood;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
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

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.service.CadastroCozinhaService;
import com.javanfood.javanfood.util.DataBaseCleaner;
import com.javanfood.javanfood.util.ResourseUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaApplicationIT {
	private static final int COZINHA_ID_INEXISTENTE = 100;

	private int cozinhasExistentes;
	private Cozinha cozinhaJaponesa;
	private String jsonCorretoCozinhaJaponesa;

	@Autowired
	private DataBaseCleaner baseCleaner;

	@LocalServerPort
	private int port;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;


	private void prepararDados() {


		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		cozinhaJaponesa = new Cozinha();
		cozinhaJaponesa.setNome("Japonesa");
		cozinhaRepository.save(cozinhaJaponesa);

		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Chinesa");
		cozinhaRepository.save(cozinha3);

		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Espanhola");
		cozinhaRepository.save(cozinha4);


		Restaurante restaurante = new Restaurante();
		restaurante.setNome("TESTE");
		restaurante.setCozinha(cozinha1);
		restaurante.setTaxaFrete(new BigDecimal(1));

		restauranteRepository.save(restaurante);

		cozinhasExistentes = (int) cozinhaRepository.count();


	}

	@BeforeEach
	public void setUp() {
		jsonCorretoCozinhaJaponesa = ResourseUtils.getContentFromResourse("/json/parametros.json");
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		baseCleaner.clearTables();
		prepararDados();

	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
				.pathParam("cozinhaId", cozinhaJaponesa.getId())
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", equalTo(cozinhaJaponesa.getNome()));
	}

	@Test
	public void deveConterCozinhas_QuandoConsultar() {

		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.body("", Matchers.hasSize(cozinhasExistentes))
				.body("nome", Matchers.hasItems("Japonesa"));
	}

	@Test
	public void cadastrar_CozinhaComSucesso() {
		Cozinha exemploCozinha = new Cozinha();
		exemploCozinha.setNome("Chinesa");

		exemploCozinha = cadastroCozinhaService.salvar(exemploCozinha);

		assertThat(exemploCozinha).isNotNull();
		assertThat(exemploCozinha.getId()).isNotNull();

	}


	@Test()
	public void deveLancarException_ao_CadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);


		assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinhaService.salvar(novaCozinha);
		});
	}

	@Test
	public void deveLancarException_QuandoExcluirCozinhaEmUso() {
		Long cozinhaId = 1L;


		assertThrows(EntidadeEmUsoExeption.class, () -> {
			cadastroCozinhaService.excluir(cozinhaId);
		});

	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Long cozinhaId = -1L;

		assertThrows(EntidadeNaoEncontradaExeption.class, () -> {
			cadastroCozinhaService.excluir(cozinhaId);
		});
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(200);
	}


	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				.body(jsonCorretoCozinhaJaponesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(201);
	}


	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaInexistente() {
		RestAssured.given()
				.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
