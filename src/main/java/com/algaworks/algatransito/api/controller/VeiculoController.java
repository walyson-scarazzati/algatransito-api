package com.algaworks.algatransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.openapi.controller.VeiculoControllerOpenApi;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.input.VeiculoInput;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.ApreensaoVeiculoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController implements VeiculoControllerOpenApi {
	
	private final VeiculoRepository veiculoRepository;
	
	private final RegistroVeiculoService registroVeiculoService;
	
	private final VeiculoAssembler veiculoAssembler;
	private final ApreensaoVeiculoService apreensaoVeiculoService;
	
	@GetMapping
	public List<VeiculoModel> listar(){
		return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
	}
	
	@GetMapping("/{veiculoId}")
	public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId){
		return veiculoRepository.findById(veiculoId)
				.map(veiculoAssembler::toModel)
				.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoModel adicionar(@Valid @RequestBody VeiculoInput veiculoInput) {
		Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
		Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);
		return veiculoAssembler.toModel(veiculoCadastrado);
	}
	
	@PutMapping("/{veiculoId}/apreensao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apreender(@PathVariable Long veiculoId) {
		apreensaoVeiculoService.apreender(veiculoId);
	}
	
	@DeleteMapping("/{veiculoId}/apreensao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerApreensao(@PathVariable Long veiculoId) {
		apreensaoVeiculoService.removerApreensao(veiculoId);
	}

}
