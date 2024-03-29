package com.douglas.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.input.RestauranteInput;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Restaurante;
@Component
public class RestauranteInputDisassembler {

	@Autowired
	ModelMapper modelMapper;
	public Restaurante toDomainObject (RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
		
		/*Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		restaurante.setCozinha(cozinha);

		return restaurante;*/
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		//Para evidar o erro: Caused by: org.hibernate.HibernateException: identifier of an instance of 
		//com.douglas.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteInput, restaurante);
	}
}
