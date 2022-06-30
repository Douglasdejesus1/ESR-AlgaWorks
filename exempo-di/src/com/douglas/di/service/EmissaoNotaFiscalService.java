package com.douglas.di.service;

import com.douglas.di.modelo.Cliente;
import com.douglas.di.modelo.Produto;
import com.douglas.di.notificacao.Notificador;
import com.douglas.di.notificacao.NotificadorEmail;

public class EmissaoNotaFiscalService {
	private Notificador notificador;
	
	

	public EmissaoNotaFiscalService(Notificador notificador) {
		super();
		this.notificador = notificador;
	}



	public void emitir(Cliente cliente, Produto produto) {
		//emite a nota fical...
		//Notificador notificador = new NotificadorEmail();
		this.notificador.notificar(cliente, "Nota fiscal do protuto: "
				+produto.getNome()+", no valor de: R$: "+produto.getValorTotal()
				+" foi emitida");
				
		
	}
}
