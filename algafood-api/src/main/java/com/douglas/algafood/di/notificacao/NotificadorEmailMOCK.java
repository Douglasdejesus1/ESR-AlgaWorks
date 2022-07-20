package com.douglas.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
@Profile("dev")
@TipoNotificador(NivelUrgencia.PRIORITARIO)
@Component
public class NotificadorEmailMOCK implements Notificador {
	
	public NotificadorEmailMOCK() {
		System.out.println("MOCK");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("SERIA NOTIFICADO O %s atraves do e-mail %s:  %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
	}
	
}