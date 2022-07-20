package com.douglas.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
@Profile("prod")
@TipoNotificador(NivelUrgencia.PRIORITARIO)
@Component
public class NotificadorEmail implements Notificador {
	
	public NotificadorEmail() {
		System.out.println("PRODUCAO");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s atraves do e-mail %s:  %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
	}
	
}