package com.douglas.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douglas.algafood.di.notificacao.Notificador;
import com.douglas.algafood.di.service.AtivacaoClienteService;

@Configuration
public class AlgaConfigService {


	
	@Bean
	public AtivacaoClienteService ativarClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}

}
