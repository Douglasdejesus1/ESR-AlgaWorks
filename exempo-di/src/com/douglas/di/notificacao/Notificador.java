package com.douglas.di.notificacao;

import com.douglas.di.modelo.Cliente;

public interface Notificador {
	
	void notificar(Cliente cliente, String mensagem);

}
