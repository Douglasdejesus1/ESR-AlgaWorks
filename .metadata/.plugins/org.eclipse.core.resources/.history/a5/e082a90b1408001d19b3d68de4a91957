package com.douglas.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;

@TipoNotificador(NivelUrgencia.NAO_PRIORITARIO)
@Component
public class NotificadorSMS implements Notificador {
	
	
	

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s atraves do SMS %s:  %s\n",
				cliente.getNome(), cliente.getTelefone(),mensagem);
	}
	
}