package com.douglas.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
				.web(WebApplicationType.NONE).run(args);
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("OutBack");
		restaurante1.setTaxaFrete(new BigDecimal(32.99));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("FreeMeet");
		restaurante2.setTaxaFrete(new BigDecimal(9.51));
		
		restaurante1 = cadastroRestaurante.salvar(restaurante1);
		restaurante2 = cadastroRestaurante.salvar(restaurante2);
		System.out.println("ID: "+restaurante1.getId()+ restaurante1.getNome() + " Taxa: " + restaurante1.getTaxaFrete());
		System.out.println("ID: "+restaurante2.getId()+ restaurante2.getNome() + " Taxa: " + restaurante2.getTaxaFrete());
	}
}
