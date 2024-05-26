package com.github.maxwelldantas.resource;

import com.github.maxwelldantas.entity.dto.request.ProjecaoJurosCompostosRequestDto;
import com.github.maxwelldantas.service.impl.ProjecaoJurosCompostosServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/juros-compostos")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjecaoJurosCompostosResource {

	private final ProjecaoJurosCompostosServiceImpl projecaoJurosCompostosService;

	@POST
	public Response calcularJurosCompostos(ProjecaoJurosCompostosRequestDto dto) {
		Double montante = projecaoJurosCompostosService.calcularMontanteFinal(dto);
		return Response.ok(montante).build();
	}

	@GET
	public Response obterMontantesFinais() {
		return Response.ok(projecaoJurosCompostosService.obterMontantesFinais()).build();
	}
}
