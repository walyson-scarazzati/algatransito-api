package com.algaworks.algatransito.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algatransito.domain.model.Veiculo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {
	
	private final RegistroVeiculoService registroVeiculoService;
	
	@Transactional
	public void apreender(Long veiculoId) {
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);	
		veiculo.aprender();
	}
	
	@Transactional
	public void removerApreensao(Long veiculoId) {
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);	
		veiculo.removerApreensao();
	}
}
