package com.douglas.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.repository.CozinhaRepository;
import com.douglas.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;
	@Autowired
	private Flyway flyway;
	@Autowired
	private DatabaseCleaner databaseCleaner;
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		propararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		//RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		given()
			 //.basePath(/cozinhas")
			//.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			//.body("", hasSize(4));
		.body("nome", hasItems("Tailandesa", "Americana"));
	}
	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body("{\"nome\":\"Chinesa\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
			
	}
	
	@Test
	public void deveRetornarStatusERespostaCorretos_QuandoConsultarCozinhaExistente() {
		
		given()
		.pathParam("cozinhaId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Americana"));
	}
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		
		given()
		.pathParam("cozinhaId", 1000)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			//.body("nome", equalTo("Americana"))
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	
	private void propararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
	}
}