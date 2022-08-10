package com.douglas.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.douglas.algafood.AlgafoodProjectApplication;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodProjectApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas= applicationContext.getBean(CozinhaRepository.class);
		List<Cozinha> todasCozinhas = cozinhas.listar();
		for(Cozinha cozinha: todasCozinhas) {
			System.out.println(cozinha.getNome());
		}
	}

}
