package br.ce.elias.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI="http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		//RestAssured.given().log().all().when().get("http://localhost:8001/tasks-backend/todo").then().log().all();
		RestAssured.given()
		.when().get("/todo").then().statusCode(200);

	}
	@Test
	public void deveAdicionarTarefaComSucesso() {
	
		RestAssured.given()
		.body("{\"task\":\"adicionado do teste\",\"dueDate\":\"2025-11-11\"}")
		.contentType(ContentType.JSON)
		.when().post("/todo").then().statusCode(201);
		
	}
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
	
		RestAssured.given()
		.body("{\"task\":\"adicionado do teste\",\"dueDate\":\"2010-11-11\"}")
		.contentType(ContentType.JSON)
		.when().post("/todo").then().statusCode(400)
		.body("message", CoreMatchers.is("Due date must not be in past") );
		
	}
}
