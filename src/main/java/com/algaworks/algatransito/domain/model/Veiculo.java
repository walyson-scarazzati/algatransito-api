package com.algaworks.algatransito.domain.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

}
