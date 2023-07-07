package com.algaworks.algatransito.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.model.input.VeiculoInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

	private final ModelMapper modelMapper; 
	
	 public Veiculo toEntity(VeiculoInput veiculoInput) {
	        return modelMapper.map(veiculoInput, Veiculo.class);
	 }

	public VeiculoModel toModel(Veiculo veiculo) {
		return modelMapper.map(veiculo, VeiculoModel.class);
	}
	
	public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
		return veiculos.stream()
				.map(this::toModel)
				.toList();
	}
}
