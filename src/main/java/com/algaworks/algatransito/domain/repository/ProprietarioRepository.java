package com.algaworks.algatransito.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algatransito.domain.model.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{

	//consulta exata
	//List<Proprietario> findByNome(String nome);
	//consulta com like
	List<Proprietario> findByNomeContaining(String nome);
	
	Optional<Proprietario> findByEmail(String email);
}
