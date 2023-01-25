package com.douglas.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.input.RestauranteInput;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Restaurante;
@Component
public class RestauranteModelDisassembler {

	public Restaurante toDomainObject (RestauranteInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		restaurante.setCozinha(cozinha);

		return restaurante;
	}
}
