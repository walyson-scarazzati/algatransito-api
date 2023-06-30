package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proprietario {
	
	@NotNull(groups = ValidationGroups.ProprietarioId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=60)
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(max=20)
	@Column(name="fone")
	private String telefone;
	
	
	
	
}
