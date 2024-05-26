package com.github.maxwelldantas.service.impl;

import com.github.maxwelldantas.entity.ProjecaoJurosCompostos;
import com.github.maxwelldantas.entity.dto.request.ProjecaoJurosCompostosRequestDto;
import com.github.maxwelldantas.repository.ProjecaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProjecaoJurosCompostosServiceImpl {

	private final ProjecaoRepository repository;

	@Transactional
	public double calcularMontanteFinal(ProjecaoJurosCompostosRequestDto projecaoJurosCompostosRequestDto) {
		double montanteFinal = projecaoJurosCompostosRequestDto.getPrincipal() *
				Math.pow((1 + projecaoJurosCompostosRequestDto.getTaxaJurosPorPeriodo()),
						projecaoJurosCompostosRequestDto.getNumeroPeriodosTotal());
		BigDecimal montanteFinalBigDecimal = BigDecimal.valueOf(montanteFinal);
		int casasDecimais = 2;
		BigDecimal montanteFinalArredondado = montanteFinalBigDecimal.setScale(casasDecimais, RoundingMode.HALF_UP);

		ProjecaoJurosCompostos projecaoJurosCompostos = ProjecaoJurosCompostos.builder().montanteFinal(montanteFinalArredondado.doubleValue()).build();

		repository.persist(projecaoJurosCompostos);

		return montanteFinalArredondado.doubleValue();
	}

	public List<ProjecaoJurosCompostos> obterMontantesFinais() {
		return repository.listAll();
	}
}
