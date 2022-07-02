package com.douglas.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douglas.algafood.di.notificacao.NotificadorEmail;
import com.douglas.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class AlgaConfig {

	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
		notificador.setCaixaAlta(false);
		return notificador;
	}

	@Bean
	public AtivacaoClienteService ativarClienteService() {
		return new AtivacaoClienteService(notificadorEmail());
	}

}
