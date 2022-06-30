package com.douglas.di;

import java.math.BigDecimal;

import com.douglas.di.modelo.Cliente;
import com.douglas.di.modelo.Produto;
import com.douglas.di.notificacao.Notificador;
import com.douglas.di.notificacao.NotificadorEmail;
import com.douglas.di.notificacao.NotificadorSms;
import com.douglas.di.service.AtivacaoClienteService;
import com.douglas.di.service.EmissaoNotaFiscalService;

public class Main {
	public static void main(String[] args) {
		Cliente joao=  new Cliente("Joao", "joao@dev.com", "322323");
		Cliente maria=  new Cliente("Maria", "maria@dev.com", "7756564");
		Notificador notificadorEmail = new NotificadorEmail();
		
		Produto carro= new Produto("golf",new BigDecimal(123000.00));
		
		AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService(notificadorEmail);
		ativacaoCliente.ativar(joao);
		ativacaoCliente.ativar(maria);
		Notificador notificadorSMS = new NotificadorSms(); 
		
		
		EmissaoNotaFiscalService emitirNota = new EmissaoNotaFiscalService(notificadorSMS);
		emitirNota.emitir(maria, carro);
		emitirNota.emitir(joao, carro);
	}

}
