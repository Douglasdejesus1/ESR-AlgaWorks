package com.douglas.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;

@Component
public class NotificadorEmail implements Notificador {
	
	private boolean caixaAlta;
	private String hostServidorSmtp;
	

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s atraves do e-mail %s:  %s\n",
				cliente.getNome(), cliente.getEmail(),mensagem);
	}
	
}