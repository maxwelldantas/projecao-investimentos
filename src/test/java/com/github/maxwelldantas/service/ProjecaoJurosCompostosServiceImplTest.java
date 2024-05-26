package com.github.maxwelldantas.service;

import com.github.maxwelldantas.entity.ProjecaoJurosCompostos;
import com.github.maxwelldantas.entity.dto.request.ProjecaoJurosCompostosRequestDto;
import com.github.maxwelldantas.repository.ProjecaoRepository;
import com.github.maxwelldantas.service.impl.ProjecaoJurosCompostosServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ProjecaoJurosCompostosServiceImplTest {

	@InjectMocks
	private ProjecaoJurosCompostosServiceImpl service;

	@Mock
	private ProjecaoRepository projecaoRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this).close();
	}

	@Test
	void testProjecaoCalculoJurosCompostos() {
		ProjecaoJurosCompostosRequestDto dto = massaDto();

		ProjecaoJurosCompostos projecaoJurosCompostos = new ProjecaoJurosCompostos();
		Mockito.doNothing().when(projecaoRepository).persist(projecaoJurosCompostos);

		double montanteFinal = service.calcularMontanteFinal(dto);
		Assertions.assertEquals(1104.71, montanteFinal);
	}

	private ProjecaoJurosCompostosRequestDto massaDto() {
		ProjecaoJurosCompostosRequestDto dto = new ProjecaoJurosCompostosRequestDto();
		dto.setPrincipal(1000);
		dto.setTaxaJurosPorPeriodo(0.008333);
		dto.setNumeroPeriodosTotal(12);
		return dto;
	}
}
