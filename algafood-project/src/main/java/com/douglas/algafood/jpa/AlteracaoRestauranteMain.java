package com.douglas.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
						.web(WebApplicationType.NONE).run(args);
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurante.setNome("FogodeChao");
		restaurante.setTaxaFrete(new BigDecimal(21.50));
		restaurante = cadastroRestaurante.salvar(restaurante);
		
		System.out.printf("%d - %s -R$ %f \n", restaurante.getId(),restaurante.getNome(), restaurante.getTaxaFrete());
		
		
}

}
