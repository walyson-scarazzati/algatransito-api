package com.algaworks.algatransito.api.model.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algatransito.domain.model.Proprietario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@SecurityRequirement(name = "security_auth")
@Tag(name = "Proprietários")
public interface ProprietarioControllerOpenApi {

	@Operation(summary = "Listar os proprietários")
	List<Proprietario> listar();

	@Operation(summary = "Buscar um proprietário por ID",
			responses = {
				@ApiResponse(responseCode = "200"),
				@ApiResponse(responseCode = "400", description = "ID da proprietário inválido",
						content = @Content(schema = @Schema(ref = "Problema"))
				),
				@ApiResponse(responseCode = "404", description = "Proprietário não encontrada",
						content = @Content(schema = @Schema(ref = "Problema"))
				)
	})
	ResponseEntity<Proprietario> buscar(@Parameter(description = "ID de uma proprietário", example = "1", required = true) Long proprietarioId);

	@Operation(summary = "Cadastrar um proprietário", description = "Cadastrar um proprietário, " +
			"necessita de um nome, telefone e email")
	Proprietario adicionar(@RequestBody(description = "Representação de uma nova proprietário", required = true) Proprietario proprietario);

	@Operation(summary = "Atualizar um proprietário por ID",
			responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID da proprietário inválido",
					content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Proprietário não encontrada",
					content = @Content(schema = @Schema(ref = "Problema"))
			)
		})
	ResponseEntity<Proprietario> atualizar(@Parameter(description = "ID de uma proprietário", example = "1", required = true) Long proprietarioId,
						  @RequestBody(description = "Representação de um proprietário com dados atualizados", required = true) Proprietario proprietario);

	@Operation(summary = "Excluir um proprietário por ID",responses = {
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "ID da proprietário inválido",
					content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Proprietário não encontrada",
					content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	ResponseEntity<Void> remover(@Parameter(description = "ID de um proprietário", example = "1", required = true)Long proprietarioId);
	
}
