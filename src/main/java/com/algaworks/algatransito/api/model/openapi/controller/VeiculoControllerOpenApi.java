package com.algaworks.algatransito.api.model.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.input.VeiculoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@SecurityRequirement(name = "security_auth")
@Tag(name = "Veículos")
public interface VeiculoControllerOpenApi {

	@Operation(summary = "Listar os veículos")
	List<VeiculoModel> listar();

	@Operation(summary = "Buscar um veículo por ID",
			responses = {
				@ApiResponse(responseCode = "200"),
				@ApiResponse(responseCode = "400", description = "ID da veículo inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
				),
				@ApiResponse(responseCode = "404", description = "veículo não encontrado",
						content = @Content(schema = @Schema(ref = "Problema"))
				)
	})
	ResponseEntity<VeiculoModel> buscar(@Parameter(description = "ID de uma veículo", example = "1", required = true) Long veiculoId);

	@Operation(summary = "Cadastrar um veículo", description = "Cadastrar um veículo, " +
			"necessita de um nome, telefone e email")
	VeiculoModel adicionar(@RequestBody(description = "Representação de uma novo veículo", required = true) VeiculoInput veiculoInput);

	@Operation(summary = "Apreender um veículo", description = "Muda o status do veículo para apreendido")
	void apreender(@Parameter(description = "Representação de uma nova Veículo", required = true) Long veiculoId);

	@Operation(summary = "Remover apreensão de um Veículo", description = "Muda o status do veículo para regular")
	void removerApreensao(@Parameter(description = "Representação de uma apreensão a ser removida", required = true) Long veiculoId);
}
