package com.douglas.algafood.di.service;

import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
import com.douglas.algafood.di.notificacao.Notificador;

public class AtivacaoClienteService {
private Notificador notificador;
	
	
	
	public AtivacaoClienteService(Notificador notificador) {
		super();
		this.notificador = notificador;
		
		System.out.println("Ativando notificador: "+notificador);
	}



	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		//Notificador notificador = new NotificadorEmail();
		this.notificador.notificar(cliente, "Seu cadastro está ativo");
		
	}
}


