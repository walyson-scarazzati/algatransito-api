package com.algaworks.algatransito.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		 var modelMapper = new ModelMapper();

	        modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
	                .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoModel::setNumeroPlaca));

	        return modelMapper;
	}
}
