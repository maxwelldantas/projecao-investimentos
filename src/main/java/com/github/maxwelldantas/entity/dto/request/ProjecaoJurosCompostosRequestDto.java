package com.github.maxwelldantas.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjecaoJurosCompostosRequestDto {

	private double principal;
	private double taxaJurosPorPeriodo;
	private int numeroPeriodosTotal;
}
