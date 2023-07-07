package com.algaworks.algatransito.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {
	
	private RegistroVeiculoService registroVeiculoService;

	@Transactional
	public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
		Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
		novaAutuacao.setVeiculo(veiculo);
		return veiculo.adicionarAutuacao(novaAutuacao); 
	}
}
