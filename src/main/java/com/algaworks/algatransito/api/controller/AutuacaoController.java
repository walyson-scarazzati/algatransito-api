package com.algaworks.algatransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

	private final RegistroAutuacaoService registroAutuacaoService;
	private final AutuacaoAssembler autuacaoAssembler;
	private final RegistroVeiculoService registroVeiculoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AutuacaoModel registrar(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInput autuacaoInput) {
		Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
		Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);
		return autuacaoAssembler.toModel(autuacaoRegistrada);
	}
	
	@GetMapping
	public List<AutuacaoModel> listar(@PathVariable Long veiculoId){
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
		return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
	}
}
