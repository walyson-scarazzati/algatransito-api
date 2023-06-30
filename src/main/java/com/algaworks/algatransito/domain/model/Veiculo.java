package com.algaworks.algatransito.domain.model;

import java.time.OffsetDateTime;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
	@NotNull
	@ManyToOne
//	@JoinColumn(name="proprietario_id")
	private Proprietario proprietario;
	
	@NotBlank
	@Size(max=20)
	private String marca;
	
	@NotBlank
	@Size(max=20)
	private String modelo;
	
	@NotBlank
	@Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
	@Size(max=7)
	private String placa;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusVeiculo status;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private OffsetDateTime dataCadastro;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private OffsetDateTime dataApreensao;

}
