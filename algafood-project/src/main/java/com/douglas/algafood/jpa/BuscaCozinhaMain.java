package com.douglas.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadastroCozinha= applicationContext.getBean(CozinhaRepository.class);
		Cozinha cozinha = cadastroCozinha.buscar(1L);
		
			System.out.println(cozinha.getNome());
		
	}

}
