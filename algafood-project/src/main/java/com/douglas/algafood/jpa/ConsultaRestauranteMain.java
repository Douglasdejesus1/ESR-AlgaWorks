package com.douglas.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		List<Restaurante> todosRestaurantes = restaurantes.listar();
		for(Restaurante restaurante: todosRestaurantes) {
			System.out.println(restaurante.getNome() + " Taxa: " + restaurante.getTaxaFrete());
		}

	}
}
