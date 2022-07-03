package com.douglas.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
import com.douglas.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	
	@Autowired
	private Notificador notificador;
	
	

	public void ativar(Cliente cliente) {
		cliente.ativar();

		
		notificador.notificar(cliente, "Seu cadastro está ativo");
		}
	

	
}
