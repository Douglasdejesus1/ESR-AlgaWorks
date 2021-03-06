package com.douglas.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.notificacao.NivelUrgencia;
import com.douglas.algafood.di.notificacao.Notificador;
import com.douglas.algafood.di.notificacao.TipoNotificador;
import com.douglas.algafood.di.service.ClienteAtivadoEvent;
@Component
public class NotificacaoService {
	
	@TipoNotificador(NivelUrgencia.PRIORITARIO)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Sua nota fiscal foi emitida!");
	}
	

}
