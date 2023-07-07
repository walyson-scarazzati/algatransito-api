package com.algaworks.algatransito.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {
	
	private final VeiculoRepository veiculoRepository;
	
	private final RegistroProprietarioService registroProprietarioService;

	public Veiculo buscar(Long veiculoId) {
		return veiculoRepository.findById(veiculoId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Veiculo não encontrado"));
	}
	
	@Transactional
	public Veiculo cadastrar(Veiculo veiculo) {
		if(veiculo.getId() != null) {
			throw new NegocioException("Veiculo a ser cadastrado não deve possuir um código");
		}
		
		boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca())
				.filter(v-> !v.equals(veiculo))
				.isPresent();
		
		if(placaEmUso) {
			throw new NegocioException("Já existe um veiculo cadastrado com esta placa");
		}
		
		Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());
		
		veiculo.setProprietario(proprietario);
		veiculo.setStatus(StatusVeiculo.REGULAR);
		veiculo.setDataCadastro(OffsetDateTime.now());
		return veiculoRepository.save(veiculo);
	}
}
