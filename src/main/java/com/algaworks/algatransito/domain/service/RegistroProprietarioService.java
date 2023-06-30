package com.algaworks.algatransito.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {
	
	private final ProprietarioRepository proprietarioRepository;
	
	public Proprietario buscar(Long proprietarioId) {
		return proprietarioRepository.findById(proprietarioId)
		.orElseThrow(() -> new NegocioException("Proprietario não encontrado"));
	}
	
	@Transactional
	public Proprietario salvar(Proprietario proprietario) {
		boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
				.filter(p-> !p.equals(proprietario))
				.isPresent();

		if(emailEmUso) {
			throw new NegocioException("Já exise um proprietario cadastrado com este email");
		}
		
		
		return proprietarioRepository.save(proprietario);
	}
	
	@Transactional
	public void excluir(Long proprietarioId) {
		proprietarioRepository.deleteById(proprietarioId);
	}
}
