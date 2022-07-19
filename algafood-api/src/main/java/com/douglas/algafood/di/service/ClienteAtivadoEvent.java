package com.douglas.algafood.di.service;

import com.douglas.algafood.di.modelo.Cliente;

public class ClienteAtivadoEvent {
	Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	

}
