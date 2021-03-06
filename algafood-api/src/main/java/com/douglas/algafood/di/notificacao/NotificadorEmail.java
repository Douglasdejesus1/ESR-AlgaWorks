package com.douglas.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
@Profile("prod")
@TipoNotificador(NivelUrgencia.PRIORITARIO)
@Component
public class NotificadorEmail implements Notificador {
	
	/*@Value("${configuration.email.conection}")
	private String host;
	
	@Value("${configuration.email.port}")
	private Integer porta;*/
	@Autowired
	public NotificadorProperties notificadorProperties;
	
	public NotificadorEmail() {
		System.out.println("PRODUCAO");
	}
	
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("Host: "+notificadorProperties.getHostServidor());
		System.out.println("Porta: "+notificadorProperties.getPortaServidor());
		System.out.printf("Notificando %s atraves do e-mail %s:  %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
	}
	
}