package com.douglas.di.notificacao;

import com.douglas.di.modelo.Cliente;

public class NotificadorSms implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s atraves do telefone %s: %s\n",
				cliente.getNome(), cliente.getTelefone(),mensagem);
	}
}
