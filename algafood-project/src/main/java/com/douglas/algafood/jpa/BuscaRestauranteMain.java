package com.douglas.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
				.web(WebApplicationType.NONE).run(args);
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
		Restaurante restaurante = cadastroRestaurante.buscar(1L);

		System.out.println(restaurante.getNome() + " Taxa: " + restaurante.getTaxaFrete()+"Cozinha: "+restaurante.getCozinha().getNome());

	}
}