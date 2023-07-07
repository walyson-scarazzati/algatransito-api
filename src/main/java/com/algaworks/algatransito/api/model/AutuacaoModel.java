package com.algaworks.algatransito.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algatransito.domain.model.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoModel {
	
	private Long id;
	private String descricao;
	private BigDecimal valorMulta;
	private OffsetDateTime dataOcorrencia;
}
