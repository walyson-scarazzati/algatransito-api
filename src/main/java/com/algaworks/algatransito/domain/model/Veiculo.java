package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algatransito.domain.exception.NegocioException;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
//	@JoinColumn(name="proprietario_id")
	private Proprietario proprietario;

	private String marca;
	
	private String modelo;

	private String placa;
	
	@Enumerated(EnumType.STRING)
	private StatusVeiculo status;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataApreensao;

	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private List<Autuacao> autuacoes = new ArrayList<>();
	
	public Autuacao adicionarAutuacao(Autuacao autuacao) {
		autuacao.setDataOcorrencia(OffsetDateTime.now());
		autuacao.setVeiculo(this);
		getAutuacoes().add(autuacao);
		return autuacao;
	}

	public void aprender() {
		if (estaApreendido()) {
			throw new NegocioException("Veiculo já se encontra apreendido");
		}
		setStatus(StatusVeiculo.APREENDIDO);
		setDataApreensao(OffsetDateTime.now());
	}

	public void removerApreensao() {
		if (naoEstaApreendido()) {
			throw new NegocioException("Veiculo não está apreendido");
		}
		setStatus(StatusVeiculo.REGULAR);
		setDataApreensao(null);
	}

	public boolean naoEstaApreendido() {
		return !estaApreendido();
	}

	public boolean estaApreendido() {
		return StatusVeiculo.APREENDIDO.equals(getStatus());
	}
}
