package com.douglas.di.service;

import com.douglas.di.modelo.Cliente;
import com.douglas.di.notificacao.Notificador;

public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	

	public AtivacaoClienteService(Notificador notificador) {
		super();
		this.notificador = notificador;
	}



	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		//Notificador notificador = new NotificadorEmail();
		this.notificador.notificar(cliente, "Seu cadastro est? ativo");
		
	}
}
