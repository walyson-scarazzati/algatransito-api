package com.algaworks.algatransito.api.model.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.domain.model.input.AutuacaoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

//@SecurityRequirement(name = "security_auth")
@Tag(name = "Atuações")
public interface AutuacaoControllerOpenApi {


	 @Operation(summary = "Cadastrar uma multa", description = "Multa cadastrada para o associado")
	AutuacaoModel registrar(@Parameter(description = "ID de um veículo", example = "1", required = true) Long veiculoId,
			@RequestBody(description = "Representação de uma atuação com dados para associar a um veículo", required = true) AutuacaoInput autuacaoInput);
	
	@Operation(summary = "Listar as multas")
	List<AutuacaoModel> listar(@PathVariable Long veiculoId);

}
