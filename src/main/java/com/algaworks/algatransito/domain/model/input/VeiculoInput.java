package com.algaworks.algatransito.domain.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoInput {
	
	//private Long proprietarioId;
	@NotNull
	@Valid
	private ProprietarioIdInput proprietario;

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
	
}
