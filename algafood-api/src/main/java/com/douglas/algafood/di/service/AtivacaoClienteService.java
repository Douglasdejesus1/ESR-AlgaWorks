package com.douglas.algafood.di.service;

import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
import com.douglas.algafood.di.notificacao.Notificador;
import com.douglas.algafood.di.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {
	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		super();
		this.notificador = notificador;

		
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		
		notificador.notificar(cliente, "Seu cadastro está ativo");
		
	}
}
