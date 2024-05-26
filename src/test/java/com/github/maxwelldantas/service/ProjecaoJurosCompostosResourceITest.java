package com.github.maxwelldantas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.maxwelldantas.entity.dto.request.ProjecaoJurosCompostosRequestDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@QuarkusTest
class ProjecaoJurosCompostosResourceITest {

	@Test
	void testCalcularJurosCompostos() {
		ProjecaoJurosCompostosRequestDto dto = new ProjecaoJurosCompostosRequestDto();
		dto.setPrincipal(1000);
		dto.setTaxaJurosPorPeriodo(0.008333);
		dto.setNumeroPeriodosTotal(12);

		RequestSpecification requisicao = RestAssured.given()
				.contentType("application/json")
				.body(dto)
				.when();
		Response resposta = requisicao.post("/juros-compostos");
		ResponseBody<?> corpoResposta = resposta.getBody();

		Assertions.assertEquals(1104.71, Double.valueOf(corpoResposta.asString()));
		Assertions.assertEquals(200, Double.valueOf(resposta.statusCode()));
	}

	@Test
	void testObterMontantesFinais() throws IOException {
		RequestSpecification requisicao = RestAssured.given()
				.when();
		Response resposta = requisicao.get("/juros-compostos");
		ResponseBody<?> corpoResposta = resposta.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		List<?> projecoesJurosCompostos = objectMapper.readValue(corpoResposta.asString(), List.class);
		LinkedHashMap<?, ?> projecaoJurosCompostos = (LinkedHashMap<?, ?>) projecoesJurosCompostos.getFirst();

		Assertions.assertEquals(1, projecoesJurosCompostos.size());
		Assertions.assertEquals(1, projecaoJurosCompostos.get("codigo"));
		Assertions.assertEquals(1104.71, projecaoJurosCompostos.get("montanteFinal"));
	}
}
