package com.douglas.algafood.di.notificacao;

import com.douglas.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}